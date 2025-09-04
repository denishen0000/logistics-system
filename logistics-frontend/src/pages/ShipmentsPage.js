import { useEffect, useState } from "react";
import api from "../api/axios";
import ShipmentCard from "../components/ShipmentCard";

export default function ShipmentsPage() {
  const [shipments, setShipments] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const pageSize = 5; // number of shipments per page

  useEffect(() => {
    api
      .get("/shipments")
      .then((res) => {
        console.log("API response:", res.data);
        setShipments(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  // callback to update the local state after saving
  const handleUpdate = (id, updatedFields) => {
    setShipments((prev) =>
      prev.map((s) =>
        s.shipmentId === id ? { ...s, ...updatedFields } : s
      )
    );
  };

  const handleCreateShipment = async () => {
    try {
      const newShipment = {
        weight: 0,
        status: "PENDING",
        pickupDate: new Date().toISOString(),
        estimatedDelivery: null,
      };
      const res = await api.post("/shipments", newShipment);
      setShipments((prev) => [res.data, ...prev]);
      setCurrentPage(1); // jump to first page so user sees the new one
    } catch (err) {
      console.error(err);
      alert("Failed to create shipment");
    }
  };

  // calculate current page data
  const indexOfLast = currentPage * pageSize;
  const indexOfFirst = indexOfLast - pageSize;
  const currentShipments = shipments.slice(indexOfFirst, indexOfLast);
  const totalPages = Math.ceil(shipments.length / pageSize);

  return (
    <div
      style={{
        display: "flex",
        justifyContent: "center",
        alignItems: "flex-start",
        padding: "50px",
        backgroundColor: "#f0f4f8",
        minHeight: "100vh",
        fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
      }}
    >
      <div
        style={{
          width: "700px",
          padding: "30px",
          border: "2px solid #ccc",
          borderRadius: "15px",
          boxShadow: "0 8px 16px rgba(0,0,0,0.2)",
          backgroundColor: "#fff",
        }}
      >
        <h1
          style={{
            fontSize: "28px",
            fontWeight: "bold",
            marginBottom: "20px",
            textAlign: "center",
          }}
        >
          Shipments
        </h1>

        <div
          style={{
            display: "flex",
            justifyContent: "center",
            marginBottom: "20px",
          }}
        >
          <button
            onClick={handleCreateShipment}
            style={{
              backgroundColor: "#10b981",
              color: "#0a0a0a",
              padding: "10px 20px",
              fontSize: "16px",
              fontWeight: "bold",
              borderRadius: "8px",
              border: "none",
              boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
              cursor: "pointer",
            }}
          >
            + Create Shipment
          </button>
        </div>

        {shipments.length === 0 ? (
          <p style={{ textAlign: "center", color: "#555" }}>
            No shipments found.
          </p>
        ) : (
          <>
            <div
              style={{
                display: "flex",
                flexDirection: "column",
                gap: "15px",
              }}
            >
              {currentShipments.map((s) => (
                <ShipmentCard
                  key={s.shipmentId}
                  shipment={s}
                  onUpdate={handleUpdate}
                />
              ))}
            </div>

            {/* Pagination controls */}
            <div
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                gap: "10px",
                marginTop: "20px",
              }}
            >
              <button
                onClick={() => setCurrentPage((p) => Math.max(p - 1, 1))}
                disabled={currentPage === 1}
                style={{
                  padding: "6px 12px",
                  borderRadius: "6px",
                  border: "1px solid #ccc",
                  backgroundColor: currentPage === 1 ? "#e5e5e5" : "#f9fafb",
                  cursor: currentPage === 1 ? "not-allowed" : "pointer",
                }}
              >
                Prev
              </button>
              <span>
                Page {currentPage} of {totalPages}
              </span>
              <button
                onClick={() =>
                  setCurrentPage((p) => Math.min(p + 1, totalPages))
                }
                disabled={currentPage === totalPages}
                style={{
                  padding: "6px 12px",
                  borderRadius: "6px",
                  border: "1px solid #ccc",
                  backgroundColor:
                    currentPage === totalPages ? "#e5e5e5" : "#f9fafb",
                  cursor:
                    currentPage === totalPages ? "not-allowed" : "pointer",
                }}
              >
                Next
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
