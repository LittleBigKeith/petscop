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

const handleOnClick = (props, foodname, unitPrice, quantity) => { 
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = props.username
    const [gold, setGold] = props.gold

    const endpointGetOwner = BASE_URL + "owner/find/name/" + username
    const endpointGetFood = BASE_URL + "food/find/name/" + foodname
    const endPointPostOwner = BASE_URL + "owner/update"
    const endPointPostOwnerFood = BASE_URL + "ownerfood/update"
    const requestOptions = {
      headers: {
          "Authorization": bearer
      }
    }

    const requestBody = {
      "quantity": quantity,
      "id": {
          "owner": {
 
          },
          "food": {
    
          }
      }
  }

    axios.get(endpointGetOwner, requestOptions)
         .then(response => {
            requestBody.id.owner = response.data
            console.log(response.data)
            axios.get(endpointGetFood, requestOptions)
                .then (response => {
                  requestBody.id.food = response.data
                  console.log(response.data)
                  requestBody.id.owner.gold -= unitPrice * quantity
                  setGold(requestBody.id.owner.gold)
                  
                  axios.post(endPointPostOwner, requestBody.id.owner, requestOptions)
                  .then(response => {
                        console.log("Update owner success!")
                        axios.post(endPointPostOwnerFood, requestBody, requestOptions)
                        .then(response => {
                              console.log("Update OwnerFood success!")
                        }).catch(error => {
                              console.log(requestBody)
                          // Can target specific HTTP error codes and display appropriate messages
                              console.log(error.response.data.message)
                          })
                  }).catch(error => {
                      // Can target specific HTTP error codes and display appropriate messages
                        console.log(error.response.data.message)
                      })
                  })
                  
              
                .catch(error => {
                  // Can target specific HTTP error codes and display appropriate messages
                      console.log(error.response.data.message)
                  })
         })
         .catch(error => {
          // Can target specific HTTP error codes and display appropriate messages
              console.log(error.response.data.message)
          })
        
}

const BuyFood = (props) => {
    const [page, setPage] = useState(0)
    const [rowsPerPage, setRowsPerPage] = useState(10)
    const [foodArray, setFoodArray] = useState([])
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = props.username
    const [gold, setGold] = props.gold
    const [quantity, setQuantity] = useState(0)
    const [rowId, setRowId] = useState(0)

    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    const loadFoods = () => {
        const endpoint = BASE_URL + "food"
    
        const requestOptions = {
            headers:{
              "Authorization":bearer
            }
          }
    
        // GET Request
        axios.get(endpoint, requestOptions)
        .then(response=>{
           setFoodArray(response.data)
           console.log(bearer)
        })
        .catch(error=>{
           console.log(error.response.data.message)
        })
        
    }

     // useEffect - run every time the component renders (dependencies are empty)
    useEffect(()=>{
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
                        </StyledTableCell> 
                        <StyledTableCell key="total">
                            {rowId === row.id? Math.round(row["price"] * (rowId === row.id) * quantity) : ""}
                        </StyledTableCell> 
                        <StyledTableCell key="buy">
                            {gold >= row["price"] * (rowId === row.id) * quantity && row["price"] * (rowId === row.id) * quantity >= 0 ? 
                            <Button variant="outlined" 
                            onClick={() => handleOnClick(props, row["name"], row["price"], quantity)}
                            style={{
                                color: "#000000",
                                borderColor: "#000000",
                                width: 100,
                                display: rowId === row.id? "block" : "none"
                                }}
                                >
                                Buy
                            </Button> :
                            <Button variant="outlined" style={{
                              color: "#FF0000",
                              borderColor: "#FF0000",
                              width: 100,
                              display: rowId === row.id? "block" : "none"
                              }}
                              disabled
                              >
                              Buy
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
        </>
      );
}

export default BuyFood;