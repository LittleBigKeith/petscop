import "../styles/BuyPet.css";
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
import DialogTitle from '@mui/material/DialogTitle';
import Dialog from '@mui/material/Dialog';
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
    { id: 'defaultName', label: 'Type', minWidth: 100 },
    {
        id: 'maxHungerPoint',
        label: 'Hunger points',
        minWidth: 100,
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

const createPet = (props) => {
    const { bearer, onClose, openDialog, username, defaultName, nickname, setNickname, maxHungerPoint, setGold } = props;

    console.log(username + " created " + defaultName, nickname + " with max hunger " + maxHungerPoint)
    
    const requestOptions = {
        headers: {
            "Authorization": bearer
        }
    } 

    const requestBody = {
        givenName: nickname,
        hungerPoint: maxHungerPoint,
        birthDate: moment().format("yyyy-MM-DD")
    }

    const endpointGetOwner = BASE_URL + "owner/find/name/" + username
    const endPointGetPet = BASE_URL + "pet/find/name/" + defaultName
    const endPointPostOwner = BASE_URL + "owner/update"
    const endpointPostOwnerPet = BASE_URL + "ownerpet/update"

    axios.get(endpointGetOwner, requestOptions)
         .then(response => {
            requestBody.owner = response.data
            console.log(response.data)

            axios.get(endPointGetPet, requestOptions)
            .then(response =>  {
                requestBody.pet = response.data
                console.log(response.data)
                
                // update Owner Table (-$)
                requestBody.owner.gold -= response.data.price
                setGold(requestBody.owner.gold)
                axios.post(endPointPostOwner, requestBody.owner, requestOptions)
                .then(response => {
                        console.log("Update owner success!")
                }).catch(error => {
                    // Can target specific HTTP error codes and display appropriate messages
                        console.log(error.response.data.message)
                })
                // update OwnerPet Table
                axios.post(endpointPostOwnerPet, requestBody, requestOptions)
                // Upon Success
                .then(response => {
                        console.log("Create Pet Success!")
                })
                // Upon failure
                .catch(error => {
                // Can target specific HTTP error codes and display appropriate messages
                    console.log(error.response.data.message)
                }
           )
           .catch(error => {
                // Can target specific HTTP error codes and display appropriate messages
                    console.log(error.response.data.message)
                })
        })
    })
}

function SimpleDialog(props) {
    const { bearer, onClose, openDialog, setOpenSnackbar, username, defaultName, nickname, setNickname, maxHungerPoint } = props;

    const handleCloseDialog = () => {
        onClose();
    }

    return (
        <Dialog className="nickname-dialog" onClose={handleCloseDialog} open={openDialog} disableRestoreFocus
        sx={{
            '.MuiPaper-root': {
              padding: 2,
            },
          }}>
            <DialogTitle>You've chosen: {defaultName}</DialogTitle>
            <DialogTitle>Give it a name!</DialogTitle>
            <form className="nickname-form" onSubmit={(e) => {e.preventDefault(); createPet(props); onClose(); setOpenSnackbar(true)}}>
                <TextField label="nickname" onChange={e => setNickname(e.target.value)} autoFocus autoComplete="off"></TextField>
                <br />
                <Button type="Submit">Confirm</Button>
            </form>
        </Dialog>
    );
}

const BuyPet = (props) => {
    const [page, setPage] = useState(0);
    const [rowsPerPage, setRowsPerPage] = useState(10);
    const [openDialog, setopenDialog] = useState(false);
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [petArray, setPetArray] = useState([])
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = props.username
    const [defaultName, setdefaultName] = useState("");
    const [nickname, setNickname] = useState("");
    const [maxHungerPoint, setMaxHungerPoint] = useState(0);
    const [gold, setGold] = props.gold

    const handleClickOpenDialog = (defaultName, maxHungerPoint) => {
        setdefaultName(defaultName)
        setMaxHungerPoint(maxHungerPoint)
        setopenDialog(true);
    };

    const handleCloseDialog = () => {
        setopenDialog(false);
    };

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

    const loadPets = () => {
        const endpoint = BASE_URL + "pet"

        const requestOptions = {
            headers: {
                "Authorization": bearer
            }
        }

        // GET Request
        axios.get(endpoint, requestOptions)
            .then(response => {
                setPetArray(response.data)
                console.log(response.data)
            })
            .catch(error => {
                console.log(error.response.data.message)
            })
    }

    // useEffect - run every time the component renders (dependencies are empty)
    useEffect(() => {
        // Run GET request in here - works as a "refresh user list"
        loadPets()
    }, [])


    return (
        <>
            <h2 className="subtitle">Buy Pet</h2>
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
                                <StyledTableCell key="buy-head">

                                </StyledTableCell>
                            </TableRow>
                        </TableHead>
                        <TableBody key="table-body">
                            {petArray
                                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                .map((row) => {
                                    return (
                                        <StyledTableRow hover role="checkbox" tabIndex={-1} key={row["defaultName"]}>
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
                                            {gold >= row["price"]?
                                            <StyledTableCell key="buy" align="center" 
                                            >
                                                <Button variant="outlined" style={{
                                                    color: "#000000",
                                                    borderColor: "#000000",
                                                    width: 100
                                                }} onClick={() => handleClickOpenDialog(row["defaultName"], row["maxHungerPoint"])}>
                                                    Buy
                                                </Button>
                                            </StyledTableCell>: 
                                            <StyledTableCell key="no buy" align="center" >
                                                <Button variant="outlined" style={{
                                                    color: "#FF0000",
                                                    borderColor: "#FF0000",
                                                    width: 100
                                                }} onClick={() => handleClickOpenDialog(row["defaultName"], row["maxHungerPoint"])}
                                                disabled>
                                                    -${(row["price"] - gold).toLocaleString('en-US')}
                                                </Button>
                                            </StyledTableCell>}
                                        </StyledTableRow>
                                    );
                                })}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 100]}
                    component="div"
                    count={petArray.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onPageChange={handleChangePage}
                    onRowsPerPageChange={handleChangeRowsPerPage}
                />
            </Paper>
            <SimpleDialog
                bearer={bearer}
                openDialog={openDialog}
                setOpenSnackbar={setOpenSnackbar}
                onClose={handleCloseDialog}
                username = {username}
                defaultName = {defaultName}
                nickname = {nickname}
                setNickname = {setNickname}
                maxHungerPoint = {maxHungerPoint}
                setGold = {setGold}
            />
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
                    {defaultName} {nickname} created successfully!
                </Alert>
            </Snackbar>
        </>
    );
}

export default BuyPet;