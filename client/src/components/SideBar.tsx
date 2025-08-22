import React, { useState } from "react";
import {
  Drawer,
  List,
  ListItemText,
  Toolbar,
  ListItemButton,
  ListItemIcon,
  useTheme,
  useMediaQuery,
  IconButton,
  Box,
} from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";
import MenuIcon from "@mui/icons-material/Menu";

const collapsedWidth = 55;
const expandedWidth = 175;

export default function Sidebar() {
  const [hovered, setHovered] = useState(false);
  const [mobileOpen, setMobileOpen] = useState(false);

  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down("lg"));

  const items = [
    { text: "Home", icon: <HomeIcon />, href: "/" },
    { text: "Login", icon: <InfoIcon />, href: "/login" },
    { text: "About", icon: <InfoIcon />, href: "/about" },
  ];

  const drawerContent = (
    <Box
      onMouseEnter={() => !isMobile && setHovered(true)}
      onMouseLeave={() => !isMobile && setHovered(false)}
    >
      <Toolbar />
      <List>
        {items.map((item) => (
          <ListItemButton
            key={item.text}
            component="a"
            href={item.href}
            sx={{
              justifyContent: hovered || isMobile ? "initial" : "center",
            }}
          >
            <ListItemIcon
              sx={{
                minWidth: 0,
                justifyContent: "center",
                mr: hovered || isMobile ? 2 : "auto",
              }}
            >
              {item.icon}
            </ListItemIcon>
            {(hovered || isMobile) && <ListItemText primary={item.text} />}
          </ListItemButton>
        ))}
      </List>
    </Box>
  );

  return (
    <>
      {isMobile && (
        <IconButton
          color="inherit"
          edge="start"
          onClick={() => setMobileOpen(true)}
          sx={{ position: "fixed", top: 8, left: 8, zIndex: 2000 }}
        >
          <MenuIcon />
        </IconButton>
      )}

      <Drawer
        variant={isMobile ? "temporary" : "permanent"}
        open={isMobile ? mobileOpen : true}
        onClose={() => setMobileOpen(false)}
        sx={{
          width: (hovered || mobileOpen) ? expandedWidth : collapsedWidth,
          flexShrink: 0,
          "& .MuiDrawer-paper": {
            width: (hovered || mobileOpen) ? expandedWidth : collapsedWidth,
            transition: "width 0.3s ease",
            overflowX: "hidden",
            position: "fixed",
            whiteSpace: "nowrap",
            height: "100vh",
          },
        }}
      >
        {drawerContent}
      </Drawer>
    </>
  );
}
