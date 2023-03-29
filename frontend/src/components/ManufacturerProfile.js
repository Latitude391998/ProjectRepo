import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

const ManufacturerProfile = () => {

    const navigate = useNavigate();

    
    const [manufacturer, setManufacturer] = useState(
        {
            brandName:"",
           address:{
               addressLine1: "",
               addressLine2: "",
               city:"",
               state: "",
               pinCode:""
           }, 
       email:"",
       mobileNumber:""
       });

    
    useEffect(() => {

            const manufacturer_data = JSON.parse(localStorage.getItem("manufacturer_info"));
            console.log(manufacturer_data);
            setManufacturer({
                 brandName:manufacturer_data.brandName,
                address:{
                    addressLine1: manufacturer_data.address.addressLine1,
                    addressLine2: manufacturer_data.address.addressLine2,
                    city: manufacturer_data.address.city,
                    state: manufacturer_data.address.state,
                    pinCode: manufacturer_data.address.pinCode
                }, 
            email:manufacturer_data.email,
            mobileNumber:manufacturer_data.mobileNumber
            })

        },[]);



    return (
        <>

            <div className="container mt-5">
                <div className="row d-flex justify-content-center">
                <div className="col-md-7">
                    <div className="card p-3 py-4">
                        <div className="text-center mt-3">

                            <div className="card-header">
                                <h5>Corporate Profile</h5>
                                <h1>{manufacturer.brandName}</h1>
                            </div>
                            <div className="card-body">
                                <div className="px-4 mt-1">
                                    <h2>{manufacturer.brandName}</h2>
                                    <h2>{manufacturer.address.addressLine1}</h2>
                                    <h2>{manufacturer.address.addressLine2}</h2>
                                    <h2>{manufacturer.address.city}</h2>
                                    <h2>{manufacturer.address.state}</h2>
                                    <h2>{manufacturer.address.pinCode}</h2>
                                    <h2>{manufacturer.email}</h2>
                                    <h2>{manufacturer.mobileNumber}</h2>
                                </div>
                            </div>
                        </div>
                        <div className="buttons" align="center">   
                            <button onClick={() => navigate('/manufacturer/profile/requests')}>View Requests</button>
                            <button onClick={() => navigate('services')}>View Service</button>
                        </div>
                    </div>
                </div>
                </div>
            </div>

        
        </>

    )
}

export default ManufacturerProfile;