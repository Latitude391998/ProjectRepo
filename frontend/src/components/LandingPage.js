import Cards from "./Cards";
import img2 from "../images/img2.png"
import { useNavigate } from "react-router-dom";
import NavBar from "./NavBar";
import AboutUs from "./AboutUs";

const LandingPage = () => {
    const navigate = useNavigate();
    return (
        <>
            <NavBar />
            <div className="container" align="center">
                <h1>Unified Services Portal</h1>

                <br />
                <img src={img2} className="img-fluid" alt="Website drawing" />


                <br />
                <br />
                <Cards />
                <br />                          
                <div className="card">
                    <div className="card-header">
                        <h5 className="card-title">The Unified Services Portal is an Application Which Aims at:</h5>
                    </div>
                    <div className="card-body">
                        <p className="card-text">
                            <ul>
                                <br />
                                   simplifing the process for seeking maintenance services or replacement of home electronic appliances based on product policy.
                                <br />
                                    management the incoming service requests by allocating appropriate authorized personnel for corporates.
                                <br />
                                    providing access to the unorganised service sector to access customers and get outsourced from corporates.
                                
                            </ul>

                        </p>
                        <button type="button" className="btn btn-primary" onClick={() => navigate('AboutUs')}>About Us</button>
                    </div>

                </div>
                    <br />                          
                    <div className="card-header">
                    </div>
                    <div className="card-header">
                        {/* <AboutUs />                           */}
                    </div>
            </div>
        </>
    );
}

export default LandingPage;