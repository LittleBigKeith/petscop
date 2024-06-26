import "../styles/PetView.css"
import {Outlet, Link} from "react-router-dom"

const PetView = () => {
    return  (
        <>
        <div className="pet-view">
            <h1>Pet View</h1>
            <div className="split">
                <div className="left">
                    <p className="box">
                    Pet image
                    </p>
                    <p className="box">
                    Pet details
                    </p>
                </div>
                <div className="right">
                    <div className="menu">
                        <Link to="" className="box">
                            Feed
                        </Link>
                        <Link to="quest" className="box">
                            Quest
                        </Link>
                    </div>
                    <Outlet />
                </div>
            </div>
        </div>
        </>
    )
}

export default PetView;