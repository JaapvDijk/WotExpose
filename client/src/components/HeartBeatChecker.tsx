import React, { useEffect } from "react";
import { Api } from "../__generated__/Api";
import Box from "@mui/material/Box";
import { heartbeatThunks, heartbeatSelectors } from "../redux/heartbeat";
import { useSelector, useDispatch } from "react-redux";

const api = new Api();

const HeartbeatChecker: React.FC = () => {
  const dispatch = useDispatch();
  const isAlive = useSelector(heartbeatSelectors.selectIsAlive);
  
useEffect(() => {
    const checkHeartbeat = async () => {
      try {
        const response = await api.heartbeat.get();
        dispatch(heartbeatThunks.setHeartbeat(response.status === 200));
      } catch (error) {
        console.error("Heartbeat check failed:", error);
        dispatch(heartbeatThunks.setHeartbeat(false));
      }
    };

    checkHeartbeat();
    const intervalId = setInterval(checkHeartbeat, 4000);

    return () => clearInterval(intervalId);
  }, [dispatch]);

  return (
    <Box className="p-4 rounded-xl shadow bg-white">
      {isAlive}
      {!isAlive && <p className="text-red-600">❌ API connection</p>}
    </Box>
  );
};
export default HeartbeatChecker;