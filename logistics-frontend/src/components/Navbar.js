import { Link, useNavigate } from "react-router-dom";

export default function Navbar() {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  const linkStyle = {
    padding: "8px 16px",
    backgroundColor: "#3b82f6",
    color: "#fff",
    borderRadius: "6px",
    textDecoration: "none",
    fontWeight: "bold",
    marginRight: "10px",
  };

  const buttonStyle = {
    padding: "8px 16px",
    backgroundColor: "#3b82f6",
    color: "#fff",
    borderRadius: "6px",
    border: "none",
    cursor: "pointer",
    fontWeight: "bold",
    marginRight: "10px",
  };

  return (
    <nav
      style={{
        padding: "15px",
        backgroundColor: "#f0f4f8",
        boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
        display: "flex",
        alignItems: "center",
      }}
    >
      <Link to="/" style={linkStyle}>
        Home
      </Link>

      {token ? (
        <button onClick={handleLogout} style={buttonStyle}>
          Logout
        </button>
      ) : (
        <>
          <Link to="/login" style={linkStyle}>
            Login
          </Link>
          <Link to="/register" style={linkStyle}>
            Register
          </Link>
        </>
      )}
    </nav>
  );
}

