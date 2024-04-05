import "../styles/Footer.css"
import email from "../assets/images/logo-email.png"
import github from "../assets/images/logo-github.png"

const Footer = () => {

    return (
        <div>
            <ul className="socialMenu">
                <li className="socialMenuText">
                    👋
                </li>
                <li className="socialMenuText">
                    created by Keith Kong
                </li>
                <li>
                    <a href="">
                        <img src={email} className="emailLogo"/>
                    </a>
                </li>
                <li>
                    <a href="">
                        <img src={github} className="githubLogo"/>
                    </a>
                </li>
            </ul>
        </div>
    )
}

export default Footer;