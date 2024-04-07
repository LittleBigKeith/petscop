import { Outlet, Link } from "react-router-dom";
import "../styles/Shop.css"

const Shop = () => {
    return  (
        <div className="shop">
            <h1>Shop</h1>
            <div className="split">
                <div className="left">
                    <div className="menu">
                        <Link to="" className="box">
                            Buy Pet
                        </Link>
                        <Link to="sell-pet" className="box">
                            Sell Pet
                        </Link>
                        <Link to="buy-food" className="box">
                            Buy Food
                        </Link>
                        <Link to="sell-food" className="box">
                            Sell Food
                        </Link>
                    </div>
                </div>
                <div className="right">
                    <Outlet />
                </div>
            </div>
        </div>
    )
}

export default Shop;