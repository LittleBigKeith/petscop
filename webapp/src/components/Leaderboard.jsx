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
    {
        id: 'username',
        label: 'Username',
        minWidth: 10,
        align: 'center',
        format: (value) => value.toLocaleString('en-US'),
    },
    {
        id: 'gold',
        label: 'Gold',
        minWidth: 10,
        align: 'center',
        format: (value) => value.toLocaleString('en-US'),
    },
];

const StyledTextField = styled(TextField)(({ theme }) => ({
    padding: 0,
}));

const Leaderboard = (props) => {
    const [page, setPage] = useState(0)
    const [rowsPerPage, setRowsPerPage] = useState(10)
    const [ownerArray, setOwnerArray] = useState([])
    const [bearer, setBearer] = props.bearer
    const [username, setUsername] = props.username

    const params = useParams()


    const handleChangePage = (event, newPage) => {
        setPage(newPage);
    };

    const handleChangeRowsPerPage = (event) => {
        setRowsPerPage(+event.target.value);
        setPage(0);
    };

    const loadOwners = () => {
        const endpoint = BASE_URL + "owner/sort/gold/desc"

        const requestOptions = {
            headers: {
                "Authorization": bearer
            }
        }

        // GET Request
        axios.get(endpoint, requestOptions)
            .then(response => {
                setOwnerArray(response.data)
            })
            .catch(error => {
                console.log(error)
            })
    }

    // useEffect - run every time the component renders (dependencies are empty)
    useEffect(() => {
        // Run GET request in here - works as a "refresh user list"
        loadOwners()
    }, [])


    return (
        <>
            <h2 className="subtitle">Leaderboard</h2>
            <Paper sx={{ width: '100%', overflow: 'hidden' }}>
                <TableContainer sx={{ maxHeight: "60vh" }}>
                    <Table stickyHeader aria-label="sticky table" key="table">
                        <TableHead key="table-head">
                            <TableRow key="table-row">
                                <StyledTableCell align="center">Rank</StyledTableCell>
                                {columns.map((column) => (
                                    <StyledTableCell
                                        key={column.id}
                                        align={column.align}
                                        style={{ minWidth: column.minWidth }}
                                    >
                                        {column.label}
                                    </StyledTableCell>
                                ))}
                            </TableRow>
                        </TableHead>
                        <TableBody key="table-body">
                            {ownerArray
                                .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                                .map((row, index) => {
                                    const color = row["username"] === username ? "red":"black"
                                    return (
                                        <StyledTableRow hover role="checkbox" tabIndex={-1} >

                                            <StyledTableCell align="center" style={{ color:color }}>
                                                {index + 1}
                                            </StyledTableCell>
                                          
                                        
                                            {columns.map((column) => {
                                                const value = row[column.id];
                                                return (
                                                    <StyledTableCell key={row[column.id]} align={column.align} style={{ color:color }}>
                                                        {column.format && typeof value === 'number'
                                                            ? column.format(value)
                                                            : value}
                                                    </StyledTableCell>
                                                );
                                            })}
                                        </StyledTableRow>
                                    );
                                })}
                        </TableBody>
                    </Table>
                </TableContainer>
                <TablePagination
                    rowsPerPageOptions={[10, 25, 100]}
                    component="div"
                    count={ownerArray.length}
                    rowsPerPage={rowsPerPage}
                    page={page}
                    onPageChange={handleChangePage}
                    onRowsPerPageChange={handleChangeRowsPerPage}
                />
            </Paper>
        </>
    );
}

export default Leaderboard;