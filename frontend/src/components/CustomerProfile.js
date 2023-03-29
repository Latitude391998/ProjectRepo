import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import CustomerProfileCSS from "./styles/CustomerProfile.css";
import Product from "../product/Product";

const CustomerProfile = () => {

    const navigate = useNavigate();
    
        const [customer, setCustomer] = useState({firstName:"dsfhs",
        lastName:"customer_data.lastName",
        address:{
            addressLine1: "addressLine1",
            addressLine2: "addressLine2",
            city: "city",
            state: "state",
            pinCode: "pinCode"
        }, 
        email:"",
        mobileNumber:""});

        
        useEffect(() => {
        const customer_data = JSON.parse(localStorage.getItem("customer_info"));
        setCustomer({
            id:customer_data.id,
            firstName:customer_data.firstName,
            lastName:customer_data.lastName,
            address:{
                addressLine1: customer_data.address.addressLine1,
                addressLine2: customer_data.address.addressLine2,
                city: customer_data.address.city,
                state: customer_data.address.state,
                pinCode: customer_data.address.pinCode
            }, 
        email:customer_data.email,
        mobileNumber:customer_data.mobileNumber
        });
    },[]);

    


    return (
        <>

            <div className="container mt-5">
                <div className="row d-flex justify-content-center">
                <div className="col-md-7">
                    <div className="card p-3 py-4">
                        <div className="text-center mt-3">
                            <div className="card-header">
                                <h5 className="mt-2 mb-0">User Profile</h5>
                            </div>
                            <div className="card-body">
                                <div className="px-4 mt-1">
                                    <h1>{customer.firstName} {customer.lastName}</h1>
                                    <h2>{customer.address.addressLine1}</h2>
                                    <h2>{customer.address.addressLine2}</h2>
                                    <h2>{customer.address.city}</h2>
                                    <h2>{customer.address.state}</h2>
                                    <h2>{customer.address.pinCode}</h2>
                                    <h2>{customer.email}</h2>
                                    <h2>{customer.mobileNumber}</h2>
                                </div>
                            </div>
                        </div>
                        <div className="buttons" align="center">
                            <button onClick={() => navigate('customer/editProfile')}>Edit Profile</button>   
                        </div>
                    </div>
                </div>
                </div>
            </div>

            <Product customerId={customer.id}></Product>

     

        </>
    )
}

export default CustomerProfile;