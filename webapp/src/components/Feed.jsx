import "../styles/Feed.css"
import { useState } from 'react';
import { useEffect } from 'react';
import Paper from '@mui/material/Paper';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TablePagination from '@mui/material/TablePagination';
import TableRow from '@mui/material/TableRow';
import { styled } from '@mui/material/styles';
import TableCell, { tableCellClasses } from '@mui/material/TableCell';
import { BASE_URL } from "../assets/baseUrl";
import axios from "axios";
import Alert from '@mui/material/Alert';
import TextField from "@mui/material/TextField";
import Button from '@mui/material/Button';
import Snackbar from '@mui/material/Snackbar';
import moment from 'moment';

const StyledTableCell = styled(TableCell)(({ theme }) => ({
  [`&.${tableCellClasses.head}`]: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
      padding: "10px 20px"
  },
  [`&.${tableCellClasses.body}`]: {
      fontSize: 14,
      padding: "10px 20px"
  },
}));

const StyledTableRow = styled(TableRow)(({ theme }) => ({
  '&:nth-of-type(odd)': {
      backgroundColor: theme.palette.action.hover,
  },
  // hide last border
  '&:last-child td, &:last-child th': {
      border: 0,
  },
}));

const columns = [
  { id_1: 'id', id_2: 'food', id_3: 'name', label: 'Type', minWidth: 100 },
  {
      id_1: 'id',
      id_2: 'food',
      id_3: 'saturation',
      label: 'Saturation',
      minWidth: 100,
      align: 'right',
      format: (value) => value.toLocaleString('en-US'),
  },

];

const StyledTextField = styled(TextField)(({ theme }) => ({
    padding: 0,
    }));

const Feed = (props) => {
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);
  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [ownerFoodArray, setOwnerFoodArray] = useState([])
  const [bearer, setBearer] = props.bearer
  const [username, setUsername] = props.username
  const [rowId, setRowId] = useState(0)
  const [quantity, setQuantity] = useState(0)

  const handleCloseSnackbar = (event, reason) => {
      if (reason === 'clickaway') {
          return;
      }

      setOpenSnackbar(false);
  };

  const handleChangePage = (event, newPage) => {
      setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
      setRowsPerPage(+event.target.value);
      setPage(0);
  };

  const loadOwnerFoods = () => {
      const endpoint = BASE_URL + "ownerfood/find/owner/" + username

      const requestOptions = {
          headers: {
              "Authorization": bearer
          }
      }

      // GET Request
      axios.get(endpoint, requestOptions)
          .then(response => {
              setOwnerFoodArray(response.data)
          })
          .catch(error => {
              console.log(error.response.data.message)
          })
  }

  // useEffect - run every time the component renders (dependencies are empty)
  useEffect(() => {
      // Run GET request in here - works as a "refresh user list"
      loadOwnerFoods()
  }, [])


  return (
      <>
          <h2 className="subtitle">Feed</h2>
          <Paper sx={{ width: '100%', overflow: 'hidden' }}>
              <TableContainer sx={{ maxHeight: "60vh" }}>
                  <Table stickyHeader aria-label="sticky table" key="table">
                      <TableHead key="table-head">
                          <TableRow key="table-row">
                              {columns.map((column) => (
                                  <StyledTableCell
                                      key={column.id}
                                      align={column.align}
                                      style={{ minWidth: column.minWidth }}
                                  >
                                      {column.label}
                                  </StyledTableCell>
                              ))}
                              <StyledTableCell key="quantity-head" align="right">
                                    Quantity
                              </StyledTableCell>
                              <StyledTableCell key="text-field" />
                              <StyledTableCell key="buy-head" />
                          </TableRow>
                      </TableHead>
                      <TableBody key="table-body">
                          {ownerFoodArray
                              .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                              .map((row) => {
                                  return (
                                      <StyledTableRow hover role="checkbox" tabIndex={-1} >
                                          {columns.map((column) => {
                                              const value = row[column.id_1][column.id_2][column.id_3];
                                              return (
                                                  <StyledTableCell key={column.id_3} align={column.align}>
                                                      {column.format && typeof value === 'number'
                                                          ? column.format(value)
                                                          : value}
                                                  </StyledTableCell>
                                              );
                                          })}
                                          <StyledTableCell key="quantity" align="right">
                                                    {row["quantity"]}
                                          </StyledTableCell>
                                          
                                          <StyledTextField
                                            error = {rowId === row.id && isNaN(Number(quantity))}
                                            variant="outlined"
                                            placeholder="0"
                                            size="small"
                                            onChange={e => {setQuantity(Number(e.target.value));
                                            console.log(rowId)}}
                                            onFocus={e => {setRowId(row.id); setQuantity(Number(e.target.value))}}
                                            autoComplete="off"
                                            autoFocus = {row.id === 1}
                                            />
                        
                                          <StyledTableCell key="use">
                                              <Button variant="outlined" style={{
                                                  color: "#000000",
                                                  borderColor: "#000000",
                                                  width: 100
                                              }}
                                              >
                                                 Use
                                              </Button>
                                          </StyledTableCell>
                                      </StyledTableRow>
                                  );
                              })}
                      </TableBody>
                  </Table>
              </TableContainer>
              <TablePagination
                  rowsPerPageOptions={[10, 25, 100]}
                  component="div"
                  count={ownerFoodArray.length}
                  rowsPerPage={rowsPerPage}
                  page={page}
                  onPageChange={handleChangePage}
                  onRowsPerPageChange={handleChangeRowsPerPage}
              />
          </Paper>
        
          <Snackbar
              open={openSnackbar}
              anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
              autoHideDuration={5000}
              onClose={handleCloseSnackbar}
          >
              <Alert
                  onClose={handleCloseSnackbar}
                  severity="success"
                  sx={{ width: '100%' }}
              >

              </Alert>
          </Snackbar>
      </>
  );
}

export default Feed;