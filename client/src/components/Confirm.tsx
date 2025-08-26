import { confirmable, createConfirmation, type ConfirmDialogProps } from 'react-confirm';
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
  Typography,
} from '@mui/material';

const MyDialog = ({
  show,
  proceed,
  message
}: ConfirmDialogProps<{ message: string }, boolean>) => (
  <Dialog
    open={show}
    onClose={() => proceed(false)}
    maxWidth="xs"
    fullWidth>
    <DialogTitle>Confirm Action</DialogTitle>
    <DialogContent>
      <Typography>{message}</Typography>
    </DialogContent>
    <DialogActions>
      <Button onClick={() => proceed(false)} color="inherit">
        Cancel
      </Button>
      <Button onClick={() => proceed(true)} variant="contained" color="primary" autoFocus>
        Yes
      </Button>
    </DialogActions>
  </Dialog>
);

export const confirm = createConfirmation(confirmable(MyDialog));