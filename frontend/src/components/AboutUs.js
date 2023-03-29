import NavBar from "./NavBar";
import { useNavigate } from "react-router-dom";


const AboutUs = () => {
    const navigate = useNavigate();
    return (
        <>
            <NavBar />
            <div className="container" align="center" bg="beige">
                <br />
                <h1>Unified Services Portal</h1>
                <br />

                <div className="row" align="center">
                    <div className="card" style={{"max-width": "71rem"}}>
                        In 2014, the average number of home appliances was 10.6 for single households, 
                        13.8 for 2-person households and 17.5 for households with 6 persons or more. 
                        Managing and maintaining the appliances becomes cumbersome as in many cases the 
                        products are from different manufacturers and each product has different warranty, 
                        guarantee, replacement and other policies regarding after-sales support.
                    </div>
                    
                </div>

                <br /><br />
                <div className="row">
                    <div className="col">
                        <div className="card border-dark mb-3" style={{"max-width": "29rem"}}>
                            <div className="card-header">CLIENT SIDE </div>
                            <div className="card-body text-dark">
                                <h5 className="card-title">After-Sales Support</h5>
                                <p className="card-text">
                                    <ul>
                                        <li>Catering to the after-sales support of domestic appliances</li>
                                        <li>Providing an end-to-end solution for customers purchasing products</li>
                                        <li>Get timely and manufacturer authourized service personnel</li>
                                        <li>A single place to cater after-sales services for all household appliances</li>
                                    </ul>
                                </p>
                            </div>
                        </div>
                    </div>

                    <div className="col">
                        <div className="card border-dark mb-3" style={{"max-width": "29rem"}}>
                            <div className="card-header">MANUFACTURER SIDE </div>
                            <div className="card-body text-dark">
                                <h5 className="card-title">After-Sales Management System</h5>
                                <p className="card-text">
                                    <ul>
                                        <li>Enabling manufacturers to efficiently manage support personnel </li>
                                        <li>One point tracking system for different incoming requests</li>
                                        <li>Analysis of different breakdowns and data on product usage</li>
                                        <li>Enabling options to deploy existing personnel by out-sourcing</li>
                                    </ul>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                                    
                <br />
                <div className="row" align="center" style={{"max-width": "71rem"}}>
                    <div className="card">
                        <h3 className="card-header">VISION</h3>
                        <p className="card-body">
                        The future scope of the application, aligning with the core objectives, include 
                        technical and financial solutions for after-sales support for commercial and 
                        industrial products, resource-management and predicting trends and automate maintenance 
                        of instruments to reduce its down-time for commercial, industrial and domestic customers.  
                        </p>
                       
                    </div>
                    
                </div>
                
            </div>
        </>
    );
}

export default AboutUs;