import React, { useEffect, useState } from "react";
import { Api } from "../__generated__/Api";
import Box from "@mui/material/Box";

const api = new Api();

const HeartbeatChecker: React.FC = () => {
  const [isOk, setIsOk] = useState<boolean | null>(null);

  useEffect(() => {
    const checkHeartbeat = async () => {
      try {
        const response = await api.heartbeat.get();
        setIsOk(response.status === 200);
      } catch (error) {
        console.error("Heartbeat check failed:", error);
        setIsOk(false);
      }
    };

    checkHeartbeat();

    const intervalId = setInterval(checkHeartbeat, 2000);

    return () => clearInterval(intervalId);
  }, []);

  return (
    <Box className="p-4 rounded-xl shadow bg-white">
      {/* {isOk === null && <p>Checking...</p>}
      {isOk === true && <p className="text-green-600">✅ API connection </p>} */}
      {isOk === false && <p className="text-red-600">❌ API connection</p>}
    </Box>
  );
};

export default HeartbeatChecker;