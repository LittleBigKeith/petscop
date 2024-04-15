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
import { useParams } from "react-router-dom";
import moment from 'moment';


// --- table boilerplate ===
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

// --- table boilerplate ===

const Feed = (props) => {
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [ownerFoodArray, setOwnerFoodArray] = useState([])
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = props.username
    const [currentHunger, setCurrentHunger] = props.currentHunger
    const [rowId, setRowId] = useState({ owner: { id: 0 }, food: { id: 0 } })
    const [quantity, setQuantity] = useState(0)
    const [selectedFoodName, setSelectedFoodName] = useState("")
    const [selectedQuantity, setSelectedQuantity] = useState(0)
    const [refreshData, setRefreshData] = useState(false);
    const params = useParams()

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

    const handleOnClick = (props, foodname, price, quantity, ownerPetId, setRefreshData) => {
        const [bearer, setBearer] = props.bearer
        const [username, setUsername] = props.username

        const endpointGetOwnerFood = BASE_URL + "ownerfood/find/key/" + username + "/" + foodname
        const endpointGetOwnerPet = BASE_URL + "ownerpet/find/id/" + ownerPetId
        const endpointGetFood = BASE_URL + "food/find/name/" + foodname
        const endPointPostOwnerFood = BASE_URL + "ownerfood/update"
        const endPointPostOwnerPet = BASE_URL + "ownerpet/update"
        const requestOptions = {
            headers: {
                "Authorization": bearer
            }
        }

        const requestBodyOwnerFood = {
            "quantity": 0,
            "id": {
                "owner": {

                },
                "food": {

                }
            }
        }

        const requestBodyOwnerPet = {
            "id": 0,
            "owner": {

            },
            "pet": {

            },
            "givenName": null,
            "birthDate": "",
            "hungerPoint": 0,
        }

        axios.get(endpointGetOwnerFood, requestOptions)
            .then(response => {
                // Construct OwnerFood request body
                requestBodyOwnerFood.id = response.data.id
                requestBodyOwnerFood.quantity = response.data.quantity
                // Deduct food item from user
                requestBodyOwnerFood.quantity -= quantity;
                axios.get(endpointGetOwnerPet, requestOptions)
                    .then(response => {
                        // Construct OwnerPet request body
                        requestBodyOwnerPet.id = response.data.id;
                        requestBodyOwnerPet.owner = response.data.owner;
                        requestBodyOwnerPet.pet = response.data.pet;
                        requestBodyOwnerPet.givenName = response.data.givenName;
                        requestBodyOwnerPet.birthDate = response.data.birthDate;
                        requestBodyOwnerPet.hungerPoint = response.data.hungerPoint;
                        axios.get(endpointGetFood, requestOptions)
                            .then(response => {
                                // Recover hunger point of OwnerPet
                                requestBodyOwnerPet.hungerPoint += response.data.saturation * quantity
                                requestBodyOwnerPet.hungerPoint = Math.min(requestBodyOwnerPet.pet.maxHungerPoint, requestBodyOwnerPet.hungerPoint)
                                axios.post(endPointPostOwnerPet, requestBodyOwnerPet, requestOptions)
                                    .then(response => {
                                        setCurrentHunger(requestBodyOwnerPet.hungerPoint)
                                    })
                                    .catch(error => {
                                        console.log(error.response.data.message)
                                    })
                            })
                            .catch(error => {

                                console.log(error.response.data.message)
                            })
                        axios.post(endPointPostOwnerFood, requestBodyOwnerFood, requestOptions)
                            .then(response => {
                                setRefreshData(true)
                            })
                            .catch(error => {
                                console.log(error.response.data.message)
                            })

                    })
                    .catch(error => {
                        console.log(error.response.data.message)
                    })
            }).catch(error => {
                console.log(error.response.data.message)
            })
    }

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
                setRowId(response.data[
                    response.data.findIndex(el => (el.id.food.id === rowId.food.id)) == -1 ?
                        0 :
                        response.data.findIndex(el => (el.id.food.id === rowId.food.id))]
                    .id)
            })
            .catch(error => {
                console.log(error)
            })
    }

    // useEffect - run every time the component renders (dependencies are empty)
    useEffect(() => {
        // Run GET request in here - works as a "refresh user list"
        loadOwnerFoods()
        setRefreshData(false)
    }, [refreshData])


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
                                .map((row, index) => {
                                    return (
                                        <StyledTableRow hover role="checkbox" tabIndex={-1} >
                                            {columns.map((column) => {
                                                const value = row[column.id_1][column.id_2][column.id_3];
                                                return (
                                                    <StyledTableCell key={row.id} align={column.align}>
                                                        {column.format && typeof value === 'number'
                                                            ? column.format(value)
                                                            : value}
                                                    </StyledTableCell>
                                                );
                                            })}

                                            <StyledTableCell key="quantity-available" align="right">
                                                {row["quantity"]}
                                            </StyledTableCell>
                                            <StyledTableCell key="quantity-feed" style={{ minWidth: 100 }}>
                                                <StyledTextField
                                                    error={rowId === row.id && (isNaN(parseInt(quantity)) || parseInt(quantity) < 0)}
                                                    variant="outlined"
                                                    placeholder="0"
                                                    size="small"
                                                    onChange={e => { setRowId(row.id); setQuantity(parseInt(e.target.value)) }}
                                                    onFocus={e => { setRowId(row.id); setQuantity(parseInt(e.target.value)) }}
                                                    autoComplete="off"
                                                    autoFocus={index === 0}
                                                />
                                            </StyledTableCell>
                                            <StyledTableCell key="use">
                                                {row["quantity"] >= quantity && (rowId === row.id) * quantity >= 0 ?
                                                    <Button variant="outlined"
                                                        onClick={() => { handleOnClick(props, row["id"]["food"]["name"], row["id"]["food"]["price"], quantity, params.id, setRefreshData); setSelectedFoodName(row["id"]["food"]["name"]); setSelectedQuantity(quantity); setOpenSnackbar(true); }}
                                                        style={{
                                                            color: "#000000",
                                                            borderColor: "#000000",
                                                            width: 100,
                                                            display: rowId === row.id ? "block" : "none"
                                                        }}
                                                    >
                                                        Use
                                                    </Button> :
                                                    <Button variant="outlined" style={{
                                                        color: "#FF0000",
                                                        borderColor: "#FF0000",
                                                        width: 100,
                                                        display: rowId.food.id === row.id.food.id ? "block" : "none"
                                                    }}
                                                        disabled
                                                    >
                                                        Use
                                                    </Button>}
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
                    Fed {selectedQuantity} {selectedFoodName}
                </Alert>
            </Snackbar>
        </>
    );
}

export default Feed;