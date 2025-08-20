import Box from "@mui/material/Box";
import Grid from '@mui/material/Grid';
import { Container } from "@mui/material";

const Footer = () => {
    return (
        <footer>
            <Box ml={4} mr={4}>
                <Container>
                    <Grid size={{xs:12, sm:6}}>
                        Footer
                    </Grid>
                </Container>
            </Box>
        </footer>
    )
}

export default Footer;