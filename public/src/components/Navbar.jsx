import '../styles/Navbar.css'

const Navbar = () => {
    return (
        <div>
        <nav>
            <ul className="navbarMenu">
                <li className="navMenuLink">
                    <a class = "navMenuLinkContent">Homepage</a>
                </li>
                <li className="navMenuLink">
                    <a class = "navMenuLinkContent">Pet Base</a>
                </li>
                <li className="navMenuLink">
                    <a class = "navMenuLinkContent">Shop</a>
                </li>
                <li className="navMenuLink">
                    <a class = "navMenuLinkContent">Quest</a>
                </li>
            </ul>
        </nav>
        </div>
    )
}

export default Navbar;