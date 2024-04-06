import './App.css'
import './index.css'
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Navbar from './components/Navbar'
import Welcome from './components/Welcome'
import PetBase from './components/PetBase'
import Shop from './components/Shop'
import PetView from './components/PetView'
import Footer from './components/Footer'
import Castle from "./assets/images/castle.png"

function App() {

  return (
    <>
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<Navbar />}>
        <Route index element={<Welcome />} />
        <Route path="pet-base" element={<PetBase />} />
        <Route path="shop" element={<Shop />} />
        <Route path="pet-view" element={<PetView />} />
      </Route>
    </Routes>
    </BrowserRouter>

    <img src={Castle} alt="Castle" className="castle" />
    <Footer/>
    </>
  )
}

export default App
