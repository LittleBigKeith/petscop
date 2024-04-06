import "../styles/PetView.css"
import { BrowserRouter, Routes, Route } from "react-router-dom"
import PetViewMenu from './PetViewMenu'
import Feed from './Feed'
import Quest from '.Quest'

const PetView = () => {
    return  (
        <>
       
        <div className="pet-view">
            <h1>Pet View</h1>
            <div className="split">
                <div className="left">
                    <div className="container">
                        <p className="box">
                        Pet image
                        </p>
                        <p className="box">
                        Pet details
                        </p>
                    </div>
                </div>
                <div className="right">
                    <PetViewMenu />
                    <Feed />
                </div>
            </div>
        </div>
        </>
    )
}

export default PetView;