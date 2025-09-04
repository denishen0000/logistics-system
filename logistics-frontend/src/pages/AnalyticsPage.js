import { useEffect, useState } from "react";
import api from "../api/axios";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  Legend,
  ResponsiveContainer,
  CartesianGrid
} from "recharts";

export default function AnalyticsPage() {
  const [data, setData] = useState([]);

  useEffect(() => {
    api.get("/analytics/avg-delivery-time")
      .then(res => setData(
        Object.entries(res.data).map(([driver, avg]) => ({ driver, avg }))
      ))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="p-6 bg-gray-100 min-h-screen">
      <h1 className="text-3xl font-bold mb-6 text-gray-800">Analytics Dashboard</h1>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        {/* Average Delivery Time Card */}
        <div className="bg-white shadow-md rounded-xl p-5">
          <h2 className="text-xl font-semibold mb-4 text-gray-700">
            Average Delivery Time per Driver
          </h2>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={data} margin={{ top: 20, right: 30, left: 0, bottom: 0 }}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="driver" />
              <YAxis />
              <Tooltip />
              <Legend />
              <Bar dataKey="avg" fill="#3b82f6" radius={[5, 5, 0, 0]} />
            </BarChart>
          </ResponsiveContainer>
        </div>

        {/* Can add more analytics cards here */}
        <div className="bg-white shadow-md rounded-xl p-5">
          <h2 className="text-xl font-semibold mb-4 text-gray-700">
            Placeholder for Another Metric
          </h2>
          <p className="text-gray-500"></p>
        </div>
      </div>
    </div>
  );
}
