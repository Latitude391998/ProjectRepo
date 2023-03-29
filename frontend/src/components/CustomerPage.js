import { Outlet } from "react-router-dom";
import NavBar from "./NavBar";


const CustomerPage = () => {
    return (
        <div>
            <NavBar />
            <br />
            <div className="container" align="center">

            <div className="card">
                <div className="card-header">
                  
                </div>
                <div className="card-body">
                        <div className="card-text"> 
                        <h1 className="card-title">Customer Sign In</h1>
                            <Outlet/>
                        </div>
                </div>
                <div className="card-header">
                  
                </div>
            </div>

            </div>
        </div>
    );
}

export default CustomerPage;