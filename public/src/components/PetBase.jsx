import "../styles/PetBase.css"
import { Outlet, Link } from "react-router-dom";

const PetBase = () => {
    return  (
        <div className="pet-base">
            <h1>Pet Base</h1>
            <div className="container">
                <Link to="/pet-view" className="box">
                A
                </Link>
                <Link to="/pet-view" className="box">
                B
                </Link>
                <Link to="/pet-view" className="box">
                C
                </Link>
                <Link to="/pet-view" className="box">
                D
                </Link>
            </div>
            <Outlet />
        </div>
    )
}

export default PetBase;