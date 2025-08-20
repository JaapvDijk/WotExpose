import React, { useState } from "react";
import {
  Drawer,
  List,
  ListItemText,
  Toolbar,
  ListItemButton,
  ListItemIcon,
  useTheme,
  ThemeProvider,
  createTheme,
} from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import InfoIcon from "@mui/icons-material/Info";

const collapsedWidth = 60;
const expandedWidth = 200;

export default function Sidebar() {
  const [hovered, setHovered] = useState(false);

    const mainTheme = useTheme();

  const theme = createTheme(mainTheme, {
    components: {
    MuiListItemButton: {
        styleOverrides: {
        root: {
            minHeight: 48,
            paddingLeft: 16,
            paddingRight: 16,
            justifyContent: hovered ? "initial" : "center",
            },
        },
        },
        MuiListItemIcon: {
        styleOverrides: {
        root: {
            minWidth: 0,
            justifyContent: "center",
            marginRight: hovered ? 24 : "auto",
          },
        },
      },
    },
  });

  const items = [
    { text: "Home", icon: <HomeIcon />, href: "/" },
    { text: "About", icon: <InfoIcon />, href: "/about" },
  ];

  return (
    <ThemeProvider theme={theme}>
      <Drawer
        variant="permanent"
        sx={{
          width: hovered ? expandedWidth : collapsedWidth,
          flexShrink: 0,
          "& .MuiDrawer-paper": {
            width: hovered ? expandedWidth : collapsedWidth,
            transition: "width 0.3s ease",
            overflowX: "hidden",
            position: "fixed",
            whiteSpace: "nowrap",
            height: "100vh",
          },
        }}
        onMouseEnter={() => setHovered(true)}
        onMouseLeave={() => setHovered(false)}
      >
        <Toolbar />
        <List>
          {items.map((item) => (
            <ListItemButton key={item.text} component="a" href={item.href}>
              <ListItemIcon>{item.icon}</ListItemIcon>
              {hovered && <ListItemText primary={item.text} />}
            </ListItemButton>
          ))}
        </List>
      </Drawer>
    </ThemeProvider>
  );
}
