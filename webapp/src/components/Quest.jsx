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
    { id: 'name', label: 'Name', minWidth: 60 },
    {
        id: 'cost',
        label: '- Hunger',
        minWidth: 10,
        align: 'right',
        format: (value) => value.toLocaleString('en-US'),
    },
];

const StyledTextField = styled(TextField)(({ theme }) => ({
    padding: 0,
}));

const Quest = (props) => {
    const [page, setPage] = useState(0)
    const [rowsPerPage, setRowsPerPage] = useState(10)
    const [openSnackbar, setOpenSnackbar] = useState(false);
    const [questArray, setQuestArray] = useState([])
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = props.username
    const [gold, setGold] = props.gold
    const [reward, setReward] = useState(0)
    const [currentHunger, setCurrentHunger] = props.currentHunger
    const [selectedQuest, setSelectedQuest] = useState("")

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

    const handleOnClick = (ownerPetId, questName) => {

        const endpointGetOwner = BASE_URL + "owner/find/name/" + username
        const endpointGetOwnerPet = BASE_URL + "ownerpet/find/id/" + ownerPetId
        const endpointGetQuest = BASE_URL + "quest/find/name/" + questName
        const endpointPostOwner = BASE_URL + "owner/update"
        const endpointPostOwnerPet = BASE_URL + "ownerpet/update"

        const requestOptions = {
            headers: {
                "Authorization": bearer
            }
        }

        const requestBodyOwner = {
            "id": 0,
            "username": "",
            "password": "",
            "role": "",
            "gold": 0,
            "cakeDate": "",
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

        setSelectedQuest(questName)
        setOpenSnackbar(true);
        axios.get(endpointGetOwner, requestOptions)
            .then(response => {
                requestBodyOwner.id = response.data.id
                requestBodyOwner.username = response.data.username
                requestBodyOwner.password = response.data.password
                requestBodyOwner.role = response.data.role
                requestBodyOwner.gold = response.data.gold
                requestBodyOwner.cakeDate = response.data.cakeDate
                axios.get(endpointGetOwnerPet, requestOptions)
                    .then(response => {
                        requestBodyOwnerPet.id = response.data.id;
                        requestBodyOwnerPet.owner = response.data.owner;
                        requestBodyOwnerPet.pet = response.data.pet;
                        requestBodyOwnerPet.givenName = response.data.givenName;
                        requestBodyOwnerPet.birthDate = response.data.birthDate;
                        requestBodyOwnerPet.hungerPoint = response.data.hungerPoint;
                        axios.get(endpointGetQuest, requestOptions)
                            .then(response => {
                                // Consume pet hunger
                                requestBodyOwnerPet.hungerPoint -= response.data.cost
                                setCurrentHunger(requestBodyOwnerPet.hungerPoint)
                                // Reward owner with gold
                                const reward = response.data.minReward + Math.floor(Math.random() * (response.data.maxReward - response.data.minReward))
                                requestBodyOwner.gold += reward
                                setReward(reward)
                                setGold(requestBodyOwner.gold)
                                axios.post(endpointPostOwner, requestBodyOwner, requestOptions)
                                    .then(response => {
                                    })
                                    .catch(error => {
                                        console.log(error.response.data.message)
                                    })
                                axios.post(endpointPostOwnerPet, requestBodyOwnerPet, requestOptions)
                                    .catch(error => {
                                        console.log(error.response.data.message)
                                    })
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

    const loadQuests = () => {
        const endpoint = BASE_URL + "quest"

        const requestOptions = {
            headers: {
                "Authorization": bearer
            }
        }

        // GET Request
        axios.get(endpoint, requestOptions)
            .then(response => {
                setQuestArray(response.data)
            })
            .catch(error => {
                console.log(error)
            })
    }

    // useEffect - run every time the component renders (dependencies are empty)
    useEffect(() => {
        // Run GET request in here - works as a "refresh user list"
        loadQuests()
    }, [])


    return (
        <>
            <h2 className="subtitle">Quest</h2>
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
                                <StyledTableCell key="reward" align="right">
                                    Reward                
                                </StyledTableCell>
                                <StyledTableCell key="button-field" />
                            </TableRow>
                        </TableHead>
                        <TableBody key="table-body">
                            {questArray
                                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                .map((row, index) => {
                                    return (
                                        <StyledTableRow hover role="checkbox" tabIndex={-1} >
                                            {columns.map((column) => {
                                                const value = row[column.id];
                                                return (
                                                    <StyledTableCell key={row[column.id]} align={column.align}>
                                                        {column.format && typeof value === 'number'
                                                            ? column.format(value)
                                                            : value}
                                                    </StyledTableCell>
                                                );
                                            })}

                                            <StyledTableCell key={row["minReward"] + row["maxReward"]} align="right">
                                                {row["minReward"]} ~ {row["maxReward"]}
                                            </StyledTableCell>

                                            <StyledTableCell key="use" align="center">
                                                {currentHunger >= row["cost"] ?
                                                    <Button variant="outlined"
                                                        onClick={() => handleOnClick(params.id, row["name"])}
                                                        style={{
                                                            color: "#000000",
                                                            borderColor: "#000000",
                                                            width: 100,

                                                        }}
                                                    >
                                                        Go
                                                    </Button> :
                                                    <Button variant="outlined" style={{
                                                        color: "#FF0000",
                                                        borderColor: "#FF0000",
                                                        width: 100,
                                                    }}
                                                        disabled
                                                    >
                                                        Go
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
                    count={questArray.length}
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
                    {selectedQuest} done! Earned {reward} Gold
                </Alert>
            </Snackbar>
        </>
    );
}

export default Quest;