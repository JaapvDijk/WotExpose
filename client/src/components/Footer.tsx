import Box from "@mui/material/Box";
import Grid from '@mui/material/Grid'

const Footer = () => {
    return (
        <footer style={{
            padding: '10px',
            borderTop: '1px dotted',
            borderBottom: '1px dotted',
        }}>
            <Box ml={4} mr={4}>
                <Grid container justifyContent='center' alignItems='center' style={{ height: '100%' }}>
                    <Grid size={{xs:12, sm:6}} style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                        Footer
                    </Grid>
                </Grid>
            </Box>
        </footer>
    )
}

export default Footer;