import "../styles/Footer.css"
import Email from "../assets/images/logo-email.png"
import Github from "../assets/images/logo-github.png"

const Footer = () => {

    return (
        <>
            <ul className="footer">
                <li className="text">
                    👋
                </li>
                <li className="text">
                    created by Keith Kong
                </li>
                <li>
                    <a href="mailto:keithkong1996@gmail.com">
                        <img src={Email} className="email"/>
                    </a>
                </li>
                <li>
                    <a href="https://github.com/LittleBigKeith">
                        <img src={Github} className="github"/>
                    </a>
                </li>
            </ul>
        </>
    )
}

export default Footer;