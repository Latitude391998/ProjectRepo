import { useRef, useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import axios from "../API/axios";
import AuthContext from "../context/AuthProvider";
import bcrypt from 'bcryptjs';


const ManufacturerLoginForm = () => {
    const userRef = useRef();
    const { setAuth } = useContext(AuthContext);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [msg, setMsg] = useState("");
    useEffect(() => {
        userRef.current.focus();
    }, []);

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {


            const response1 = await axios.post("/manufacturer/check",JSON.stringify({email}) , {
                headers: { "Content-Type": 'application/json' }
            });

            const hashedPassword = (await response1).data.password;

            const validUser = bcrypt.compareSync(password, hashedPassword);
            console.log(validUser);

            if(validUser){

            const payload = {email:email, password:hashedPassword};

            const response = await axios.post("/manufacturer/login", JSON.stringify(payload),
                {
                    headers: { "Content-Type": 'application/json' }
                });

                const manufacturerData = (await response).data;
                localStorage.setItem("manufacturer_info",JSON.stringify(manufacturerData));
                navigate('/manufacturer/profile');

            }
            
        } catch (error) {
            setMsg("Invalid Email Id or Password");
        }
        setAuth({ email, password });
        setEmail("");
        setPassword("");
    }

    return (
        <>
                <div className="container-sm">
                <div className="card-header text-center fs-3">
                    {msg && <p className="text-success">{msg}</p>}
                </div>
                    <form onSubmit={handleSubmit}>
                        <div className="form-outline mb-4">
                            <label className="form-label" htmlFor="form2Example1">Email address</label>
                            <input type="email" id="email" ref={userRef} onChange={(e => setEmail(e.target.value))} placeholder="E-Mail" value={email} required autoComplete="off" className="form-control row mb-4" />
                        </div>

                        <div className="form-outline mb-4">
                            <label className="form-label" htmlFor="form2Example2">Password</label>
                            <input type="password" id="password" onChange={(e => setPassword(e.target.value))} placeholder="Password" value={password} required className="form-control" />
                        </div>
                        <div className="row mb-4">
                            <div className="col d-flex justify-content-center">
                                <div className="form-check">
                                    <input className="form-check-input" type="checkbox" value="" id="form2Example31" />
                                    <label className="form-check-label" htmlFor="form2Example31"> Remember me </label>
                                </div>
                            </div>

                            <div className="col">
                                <button onClick={() => navigate('/forgot')}>Forgot password?</button>
                            </div>
                        </div>

                        <button type="submit" className="btn btn-primary btn-block mb-4">Sign in</button>

                        <div className="text-center">
                            <p>Not a member? <button onClick={() => navigate('/manufacturer/register')}>Register</button></p>
                        </div>
                    </form>
                </div>
        </>
    );
}

export default ManufacturerLoginForm;