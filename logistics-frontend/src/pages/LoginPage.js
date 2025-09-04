import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", {
        email,
        password,
      });

      localStorage.setItem("token", response.data); 
      navigate("/shipments");
    } catch (err) {
      setError("Invalid email or password");
    }
  };

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
        backgroundColor: "#f0f4f8",
      }}
    >
      <div
        style={{
          width: "500px",
          padding: "40px",
          border: "2px solid #ccc",
          borderRadius: "15px",
          boxShadow: "0 8px 16px rgba(0,0,0,0.2)",
          backgroundColor: "#fff",
        }}
      >
        <h2 style={{ textAlign: "center", fontWeight: "bold", marginBottom: "25px", fontSize: "28px" }}>Login</h2>
        <form onSubmit={handleLogin} style={{ display: "flex", flexDirection: "column", gap: "20px" }}>
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            style={{
              padding: "15px",
              fontSize: "16px",
              border: "1px solid #aaa",
              borderRadius: "8px",
              outline: "none",
            }}
          />

          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
            style={{
              padding: "15px",
              fontSize: "16px",
              border: "1px solid #aaa",
              borderRadius: "8px",
              outline: "none",
            }}
          />

          <button
            type="submit"
            style={{
              padding: "15px",
              fontSize: "18px",
              backgroundColor: "#3b82f6",
              color: "white",
              border: "none",
              borderRadius: "8px",
              cursor: "pointer",
              fontWeight: "bold",
            }}
          >
            Login
          </button>
        </form>

        {error && <p style={{ color: "red", marginTop: "20px", fontWeight: "bold", textAlign: "center" }}>{error}</p>}
      </div>
    </div>
  );
}
