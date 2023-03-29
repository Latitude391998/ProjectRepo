import { NavLink } from "react-router-dom"


const Cards = () => {
    return (

        <div className="card-group">

            <div className="card">
                {/* <img src="..." className="card-img-top" alt="..."/> */}
                <div className="card-body">
                    <h5 className="card-title">What We Do</h5>
                    <p className="card-text"> We simplifiy the process of maintaining and repairing your home appliances</p>
                    <p className="card-text"><small className="text-muted">
                        {/* <NavLink className="nav-link" to="/AboutUs">Read More</NavLink>  */}
                    </small></p>
                </div>
            </div>
            <div className="card">
                {/* <img src="..." className="card-img-top" alt="..."/> */}
                <div className="card-body">
                    <h5 className="card-title">For Customers</h5>
                    <p className="card-text">Hassle free requesting, tracking and resolving services of after-sales services of all appliances at a single portal.</p>
                    <p className="card-text"><small className="text-muted">
                    {/* <NavLink className="nav-link" to="/AboutUs">Read More</NavLink>  */}
                    </small></p>
                </div>
            </div>
            <div className="card">
                {/* <img src="..." className="card-img-top" alt="..."/> */}
                <div className="card-body">
                    <h5 className="card-title">For Manufacturers</h5>
                    <p className="card-text">Easy tracking for after-sales services and resource management.</p>
                    <p className="card-text"><small className="text-muted">
                    {/* <NavLink className="nav-link" to="/AboutUs">Read More</NavLink>  */}
                    </small></p>
                </div>
            </div>
        </div>

    )

}

export default Cards;