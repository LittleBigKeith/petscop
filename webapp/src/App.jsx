import './App.css'
import './index.css'
import { BrowserRouter, Routes, Route } from "react-router-dom"
import { useState } from 'react'
import Navbar from './components/Navbar'
import Welcome from './components/Welcome'
import PetBase from './components/PetBase'
import Shop from './components/Shop'
import BuyPet from './components/BuyPet'
import BuyFood from './components/BuyFood'
import PetView from './components/PetView'
import Feed from './components/Feed'
import Quest from './components/Quest'
import Footer from './components/Footer'
import Castle from "./assets/images/castle.png"
import Login from './components/Login'
import Register from './components/Register'
import Leaderboard from './components/Leaderboard'


function App() {

  const [bearer, setBearer] = useState("")
  const [username, setUsername] = useState("")
  const [gold, setGold] = useState(0)
  const [currentHunger, setCurrentHunger] = useState(0)
  const [loginMsg, setLoginMsg] = useState("")
  const [loginMsgSeverity, setLoginMsgSeverity] = useState("")

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={bearer.length > 0? <Navbar bearer={[bearer,setBearer]} username={[username,setUsername]} gold={[gold, setGold]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]} />: null}>
            <Route path="" element={<Welcome />}>
              <Route path = "" element={<Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]}/>} />
              <Route path = "register" element={<Register bearer={[bearer,setBearer]} username={[username,setUsername]} />} />
            </Route>
            <Route path="pet-base/:username" element={bearer.length > 0? <PetBase bearer={[bearer,setBearer]} username={[username,setUsername]} />:<Welcome /> }>
              <Route path="" element={bearer.length > 0? null:<Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]}/>} />
            </Route>
            <Route path="shop" element={bearer.length > 0? <Shop />:<Welcome />}>
              <Route path="" element={bearer.length > 0? <BuyPet bearer={[bearer,setBearer]} username={[username,setUsername]} gold={[gold, setGold]} />:<Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]}/>} />
              <Route path="buy-food" element={bearer.length > 0? <BuyFood bearer={[bearer,setBearer]} username={[username,setUsername]} gold={[gold, setGold]}/>:<Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]}/>} />
            </Route>
            <Route path="leaderboard" element={bearer.length > 0? <Leaderboard  bearer={[bearer,setBearer]} username={[username,setUsername]} />:<Welcome />}>
              <Route path="" element={bearer.length > 0? null:<Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]}/>} />
            </Route>
            <Route path="pet-view/:id" element={bearer.length > 0? <PetView bearer={[bearer,setBearer]} username={[username,setUsername]} currentHunger={[currentHunger, setCurrentHunger]}/>: <Welcome /> }>
              <Route path="" element={bearer.length > 0? <Feed bearer={[bearer,setBearer]} username={[username,setUsername]} currentHunger={[currentHunger, setCurrentHunger]} />: <Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]} />} />
              <Route path="quest" element={bearer.length > 0? <Quest bearer={[bearer,setBearer]} username={[username,setUsername]} gold={[gold, setGold]} currentHunger={[currentHunger, setCurrentHunger]} />: <Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]}/>} />
            </Route>
            <Route path="*" element={bearer.length > 0? <PetBase bearer={[bearer,setBearer]} username={[username,setUsername]} />:<Welcome /> }>
              <Route path="*" element={bearer.length > 0? null:<Login bearer={[bearer,setBearer]} username={[username,setUsername]} loginMsg={[loginMsg, setLoginMsg]} loginMsgSeverity={[loginMsgSeverity, setLoginMsgSeverity]} />} />
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
