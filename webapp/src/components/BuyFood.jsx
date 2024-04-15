import "../styles/BuyFood.css"
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
import TextField from "@mui/material/TextField";
import Button from "@mui/material/Button";
import Snackbar from '@mui/material/Snackbar';
import Alert from '@mui/material/Alert'

// --- table boilerplate code starts ---
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

const StyledTextField = styled(TextField)(({ theme }) => ({
  padding: 0,
}));

// change your display columns here
// id: the data of your class
// label: the display column title of table
const columns = [
  { id: 'name', label: 'Name', minWidth: 60 },
  {
    id: 'saturation',
    label: 'Saturation',
    minWidth: 10,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  },
  {
    id: 'price',
    label: 'Price',
    minWidth: 10,
    align: 'right',
    format: (value) => value.toLocaleString('en-US'),
  },
];

// --- table boilerplate code ends ---

// when BUY button is clicked
const handleOnClick = (props, foodname, unitPrice, quantity) => {
  const [bearer, setBearer] = props.bearer
  const [username, setUsername] = props.username
  const [gold, setGold] = props.gold

  const endpointGetOwner = BASE_URL + "owner/find/name/" + username
  const endpointGetFood = BASE_URL + "food/find/name/" + foodname
  const endpointGetOwnerFood = BASE_URL + "ownerfood/find/key/" + username + "/" + foodname
  const endPointPostOwner = BASE_URL + "owner/update"
  const endPointPostOwnerFood = BASE_URL + "ownerfood/update"
  const requestOptions = {
    headers: {
      "Authorization": bearer
    }
  }

  // request body for OwnerFood
  const requestBody = {
    "quantity": quantity,
    "id": {
      "owner": {

      },
      "food": {

      }
    }
  }

  // Post to ownerFood
  const PostData = () => {
    axios.post(endPointPostOwner, requestBody.id.owner, requestOptions)
      .then(response => {
        axios.post(endPointPostOwnerFood, requestBody, requestOptions)
          .catch(error => {
            console.log(error.response.data.message)
          })
      }).catch(error => {
        console.log(error.response.data.message)
      })
  }

  
  axios.get(endpointGetOwner, requestOptions)
    .then(response => {
      // Construct post body with Owner struct
      requestBody.id.owner = response.data
      axios.get(endpointGetFood, requestOptions)
        .then(response => {
          // Construct post body with Food struct
          requestBody.id.food = response.data
          // Deduct money from owner
          requestBody.id.owner.gold -= unitPrice * quantity
          // Update state variable
          setGold(requestBody.id.owner.gold)
          axios.get(endpointGetOwnerFood, requestOptions)
            .then(response => {
              // Add quantity to OnwerFood
              requestBody.quantity += response.data.quantity
              PostData()
            }).catch(error => {
              console.log(error.response.data.message)
              PostData()
            })
        })
        .catch(
          error => {
            console.log(error.response.data.message)
          }
        )
        .catch(error => {
          console.log(error.response.data.message)
        })
    })
    .catch(error => {
      console.log(error.response.data.message)
    })
}

const BuyFood = (props) => {
  const [page, setPage] = useState(0)
  const [rowsPerPage, setRowsPerPage] = useState(10)
  const [foodArray, setFoodArray] = useState([])
  const [selectedFoodName, setSelectedFoodName] = useState("")
  const [bearer, setBearer] = props.bearer
  const [gold, setGold] = props.gold
  const [quantity, setQuantity] = useState(0)
  const [selectedQuantity, setSelectedQuantity] = useState(0)
  const [rowId, setRowId] = useState(0)
  const [openSnackbar, setOpenSnackbar] = useState(false);


  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  const handleCloseSnackbar = (event, reason) => {
    if (reason === 'clickaway') {
      return;
    }

    setOpenSnackbar(false);
  };

  const loadFoods = () => {
    const endpoint = BASE_URL + "food"

    const requestOptions = {
      headers: {
        "Authorization": bearer
      }
    }

    // GET Request
    axios.get(endpoint, requestOptions)
      .then(response => {
        setFoodArray(response.data)
        console.log(bearer)
      })
      .catch(error => {
        console.log(error.response.data.message)
      })

  }

  // useEffect - run every time the component renders (dependencies are empty)
  useEffect(() => {
    // Run GET request in here - works as a "refresh user list"
    loadFoods()
  }, [])


  return (
    <>
      <h2 className="subtitle">Buy Food</h2>
      <Paper sx={{ width: '100%', overflow: 'hidden' }}>
        <TableContainer sx={{ maxHeight: "60vh" }}>
          <Table stickyHeader aria-label="sticky table">
            <TableHead>
              <TableRow>
                {columns.map((column) => (
                  <StyledTableCell
                    key={column.id}
                    align={column.align}
                    style={{ minWidth: column.minWidth }}
                  >
                    {column.label}
                  </StyledTableCell>
                ))}
                <StyledTableCell
                  style={{ minWidth: 100 }}>
                  Quantity
                </StyledTableCell>
                <StyledTableCell
                  style={{ minWidth: 100 }}>
                  Total
                </StyledTableCell>
                <StyledTableCell>

                </StyledTableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {foodArray
                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                .map((row) => {
                  return (
                    <StyledTableRow hover role="checkbox" tabIndex={-1} key={row["name"]}>
                      {columns.map((column) => {
                        const value = row[column.id];
                        return (
                          <StyledTableCell key={column.id} align={column.align}>
                            {column.format && typeof value === 'number'
                              ? column.format(value)
                              : value}
                          </StyledTableCell>
                        );
                      })}
                      <StyledTableCell key="quantity">
                        <StyledTextField
                          error={rowId === row.id && (isNaN(parseInt(quantity)) || (parseInt(quantity) < 0))}
                          variant="outlined"
                          placeholder="0"
                          size="small"
                          onChange={e => {
                            setQuantity(parseInt(e.target.value));
                            console.log(rowId)
                          }}
                          onFocus={e => { setRowId(row.id); setQuantity(parseInt(e.target.value)) }}
                          autoComplete="off"
                          autoFocus={row.id === 1}
                        />
                      </StyledTableCell>
                      <StyledTableCell key="total">
                        {rowId === row.id ? Math.round(row["price"] * (rowId === row.id) * quantity) : ""}
                      </StyledTableCell>
                      <StyledTableCell key="buy">
                        {gold >= row["price"] * (rowId === row.id) * quantity && row["price"] * (rowId === row.id) * quantity >= 0 ?
                          <Button variant="outlined"
                            onClick={() => { handleOnClick(props, row["name"], row["price"], quantity); setSelectedFoodName(row["name"]); setSelectedQuantity(quantity); setOpenSnackbar(true); }}
                            style={{
                              color: "#000000",
                              borderColor: "#000000",
                              width: 100,
                              display: rowId === row.id ? "block" : "none"
                            }}
                          >
                            Buy
                          </Button> :
                          <Button variant="outlined" style={{
                            color: "#FF0000",
                            borderColor: "#FF0000",
                            width: 100,
                            display: rowId === row.id ? "block" : "none"
                          }}
                            disabled
                          >
                            -${(row["price"] * quantity - gold).toLocaleString('en-US')}
                          </Button>
                        }
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
          count={foodArray.length}
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
          {selectedQuantity} {selectedFoodName} bought!
        </Alert>
      </Snackbar>
    </>
  );
}

export default BuyFood;