import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../API/axios";
import NavBar from "./NavBar";

import bcrypt from 'bcryptjs'


const SignUpFormManufacturer = () => {

    const[brandName, setbrandName] = useState("");
    
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [addressLine1, setAddressLine1] = useState("");
    const [addressLine2, setAddressLine2] = useState("");
    const [city, setCity] = useState("");
    const [state, setState] = useState("");
    const [pinCode, setPinCode] = useState(0);
    const [mobileNumber, setMobileNumber] = useState(0);
    const [type, setType] = useState("");
    const [msg, setMsg] = useState("");

    const navigate = useNavigate();

    async function register(event) {
        event.preventDefault();

        const salt = bcrypt.genSaltSync(10)

        const hashedPassword = bcrypt.hashSync(password, salt);


        console.log(brandName, password, email, mobileNumber);
        setbrandName("");
        setPassword("");
        setEmail("");
        setAddressLine1("");
        setAddressLine2("");
        setCity("");
        setState("");
        setPinCode("");
        setMobileNumber(0);
        setType("MANUFACTURER");
        let address = {
            addressLine1: addressLine1,
            addressLine2: addressLine2,
            city: city,
            state: state,
            pinCode: pinCode
        };
        const obj = { brandName: brandName, address: address, email: email, password: hashedPassword, mobileNumber: mobileNumber, type: "MANUFACTURER" };
        

        console.log(obj);
        try {
            const response = await axios.post("/manufacturer/register", JSON.stringify(obj),
            {
                headers: { "Content-Type": 'application/json' }
            });
        } catch (error) {
            setMsg("Invalid");
            navigate('/register');
        }
        navigate('/manufacturer');
        
        // const response = await axios.post("/manufacturer/register", JSON.stringify(obj),
        //         {
        //             headers: { "Content-Type": 'application/json' }
        //         });
        // console.log(response);
        //navigate('/manufacturer');
    }

    return (
        <>
            <div className="container-sm">
                <form onSubmit={register}>
                    
                    <div className="row">
                        
                        <div className="col">
                            <label htmlFor="brandName">Brand Name: </label><br />
                            <input type="text" id="brandName" onChange={(e => setbrandName(e.target.value))} className="form-control" placeholder="Brand name" required/>
                        </div>
                    </div>
                    <br />
                    <div className="form-group">
                        <label htmlFor="email">E-Mail: </label>
                        <input type="email" className="form-control" name="email" id="email" onChange={(e => setEmail(e.target.value))} placeholder="E-Mail" required/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password: </label>
                        <input type="password" className="form-control" name="password" id="password" onChange={(e => setPassword(e.target.value))} placeholder="Password" required/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="mNumber">Mobile Number: </label>
                        <input type="tel" className="form-control" id="mNumber" onChange={(e => setMobileNumber(e.target.value))} placeholder="Mobile Number" required/>
                    </div>
                    <div className="row">
                        <div className="col">
                            <label htmlFor="addressLine1">Address Line 1: </label><br />
                            <input type="text" id="add1" onChange={(e => setAddressLine1(e.target.value))} className="form-control" placeholder="Line 1" required/>
                        </div>
                        <div className="col">
                            <label htmlFor="addressLine2">Address Line 2: </label><br />
                            <input type="text" id="add2" onChange={(e => setAddressLine2(e.target.value))} className="form-control" placeholder="Line 2" required/>
                        </div>
                    </div><br />
                    <div className="row">
                        <div className="col">
                            <label htmlFor="city">City: </label><br />
                            <input type="text" name="city" id="city" onChange={(e => setCity(e.target.value))} placeholder="City" className="form-control col-md-10" required/>
                        </div>
                        <div className="col">
                            <label htmlFor="state">State: </label><br />
                            <input type="text" name="state" id="state" onChange={(e => setState(e.target.value))} placeholder="State" className="form-control col-md-10" required/>
                        </div>
                        <div className="col">
                            <label htmlFor="pinCode">Pin Code: </label><br />
                            <input type="number" name="pinCode" id="pinCode" onChange={(e => setPinCode(e.target.value))} placeholder="Pin Code" className="form-control col-md-10" required/>
                        </div>
                        {/* <div className="col">
                            <label htmlFor="type">Type: </label>
                            <select id="type" className="form-control" onChange={(e => setType(e.target.value))}>
                                <option defaultValue="">Choose...</option>
                                <option value="CUSTOMER">Customer</option>
                                <option value="MANUFACTURER">Manufacturer</option>
                            </select>
                        </div> */}
                    </div>
                    <br />
                    <input type="submit" className="mx-auto btn btn-primary" style={{ width: "200px" }} value="Register" />
                    <input type="reset" className="btn btn-danger" value="Reset" />

                </form>
            </div>
        </>
    );
}

export default SignUpFormManufacturer;