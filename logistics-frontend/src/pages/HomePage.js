export default function HomePage() {
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
          width: "600px",
          padding: "50px",
          border: "2px solid #ccc",
          borderRadius: "15px",
          boxShadow: "0 8px 16px rgba(0,0,0,0.2)",
          backgroundColor: "#fff",
          textAlign: "center",
        }}
      >
        <h1 style={{ fontSize: "32px", fontWeight: "bold", marginBottom: "20px" }}>
          Welcome to Logistics Dashboard
        </h1>
        <p style={{ fontSize: "18px", color: "#555", marginBottom: "30px" }}>
          Use the navigation bar to view shipments and analytics.
        </p>

        <div style={{ display: "flex", justifyContent: "center", gap: "20px" }}>
          <a
            href="/shipments"
            style={{
              padding: "12px 25px",
              backgroundColor: "#3b82f6",
              color: "#fff",
              borderRadius: "8px",
              textDecoration: "none",
              fontWeight: "bold",
            }}
          >
            Shipments
          </a>
          <a
            href="/analytics"
            style={{
              padding: "12px 25px",
              backgroundColor: "#10b981",
              color: "#fff",
              borderRadius: "8px",
              textDecoration: "none",
              fontWeight: "bold",
            }}
          >
            Analytics
          </a>
        </div>
      </div>
    </div>
  );
}
