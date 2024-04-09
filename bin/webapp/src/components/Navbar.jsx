import "../styles/Navbar.css"
import { Outlet, Link } from "react-router-dom";

const Navbar = () => {
    return (
        <>
        <nav className="navbar">
            <div className="stats">
                Gold: 0     Exp: 0
            </div>
            <div className="navigation">
                <ul className="menu">
                    <li className="link">
                        <Link to="/" className="text">Home</Link>
                    </li>
                    <li className="link">
                        <Link to="/pet-base" className="text">Pet Base</Link>
                    </li>
                    <li className="link">
                        <Link to="/shop" className="text">Shop</Link>
                    </li>
                </ul>
            </div>
        </nav>

        <Outlet />
        </>
    )
}

export default Navbar;