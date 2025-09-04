import { useState, useMemo } from "react";
import api from "../api/axios";

export default function ShipmentCard({ shipment, onUpdate }) {
  const [weight, setWeight] = useState(shipment.weight);
  const [pickupDate, setPickupDate] = useState(shipment.pickupDate);
  const [estimatedDelivery, setEstimatedDelivery] = useState(shipment.estimatedDelivery);
  const [deliveryDate, setDeliveryDate] = useState(shipment.deliveryDate);
  const [loading, setLoading] = useState(false);

  const status = useMemo(() => {
    const now = new Date();
    if (deliveryDate) return "DELIVERED";
    if (estimatedDelivery && new Date(estimatedDelivery) < now) return "DELAYED";
    return "IN_TRANSIT";
  }, [deliveryDate, estimatedDelivery]);

  const handleSave = async () => {
    setLoading(true);
    try {
      // send everything you want to update to your API
      await api.put(`/shipments/${shipment.shipmentId}`, {
        weight: parseFloat(weight),
        pickupDate,
        estimatedDelivery,
        deliveryDate,
      });

      // update local state in parent
      onUpdate(shipment.shipmentId, {
        weight: parseFloat(weight),
        pickupDate,
        estimatedDelivery,
        deliveryDate,
      });
    } catch (err) {
      console.error(err);
      alert("Failed to update shipment");
    }
    setLoading(false);
  };

  return (
    <div className="border p-4 mb-2 rounded shadow bg-white">
      <p>
        <strong>ID:</strong> {shipment.shipmentId}
      </p>

      <p className="mb-2">
        <strong>Status:</strong>{" "}
        <span
          className={`inline-block px-2 py-1 rounded text-white ${
            status === "DELIVERED"
              ? "bg-green-500"
              : status === "DELAYED"
              ? "bg-red-500"
              : "bg-yellow-500"
          }`}
        >
          {status}
        </span>
      </p>

      {/* Weight */}
      <div className="mb-2">
        <label className="block font-semibold mb-1">Weight (kg):</label>
        <input
          type="number"
          value={weight}
          onChange={(e) => setWeight(e.target.value)}
          className="border rounded px-2 py-1 w-full"
        />
      </div>

      {/* Pickup Date */}
      <div className="mb-2">
        <label className="block font-semibold mb-1">Pickup Date:</label>
        <input
          type="datetime-local"
          value={pickupDate ? new Date(pickupDate).toISOString().slice(0, 16) : ""}
          onChange={(e) => setPickupDate(new Date(e.target.value).toISOString())}
          className="border rounded px-2 py-1 w-full"
        />
      </div>

      {/* Estimated Delivery */}
      <div className="mb-2">
        <label className="block font-semibold mb-1">Estimated Delivery:</label>
        <input
          type="datetime-local"
          value={estimatedDelivery ? new Date(estimatedDelivery).toISOString().slice(0, 16) : ""}
          onChange={(e) => setEstimatedDelivery(new Date(e.target.value).toISOString())}
          className="border rounded px-2 py-1 w-full"
        />
      </div>

      {/* Delivery Date */}
      <div className="mb-2">
        <label className="block font-semibold mb-1">Delivery Date:</label>
        <input
          type="datetime-local"
          value={deliveryDate ? new Date(deliveryDate).toISOString().slice(0, 16) : ""}
          onChange={(e) => setDeliveryDate(new Date(e.target.value).toISOString())}
          className="border rounded px-2 py-1 w-full"
        />
      </div>

      <button
        onClick={handleSave}
        disabled={loading}
        style={{
          backgroundColor: "#3b82f6",
          color: "black",
          padding: "10px 20px",
          fontSize: "14px",
          fontWeight: "bold",
          borderRadius: "8px",
          border: "none",
          boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
          cursor: "pointer",
        }}
        className="mt-3 px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-600 disabled:opacity-50"
      >
        {loading ? "Saving..." : "Save"}
      </button>
    </div>
  );
}
