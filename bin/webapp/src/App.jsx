import './App.css'
import './index.css'
import { BrowserRouter, Routes, Route } from "react-router-dom"
import Navbar from './components/Navbar'
import Welcome from './components/Welcome'
import PetBase from './components/PetBase'
import Shop from './components/Shop'
import BuyPet from './components/BuyPet'
import SellPet from './components/SellPet'
import BuyFood from './components/BuyFood'
import SellFood from './components/SellFood'
import PetView from './components/PetView'
import Feed from './components/Feed'
import Quest from './components/Quest'
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
            <Route path="shop" element={<Shop />}>
              <Route path="" element={<BuyPet />} />
              <Route path="sell-pet" element={<SellPet />} />
              <Route path="buy-food" element={<BuyFood />} />
              <Route path="sell-food" element={<SellFood />} />
        </Route>
            <Route path="pet-view" element={<PetView />}>
              <Route path="" element={<Feed />} />
              <Route path="quest" element={<Quest />} />
            </Route>
          </Route>
        </Routes>
      </BrowserRouter>

      <img src={Castle} alt="Castle" className="castle" />
      <Footer />
    </>
  )
}

export default App
