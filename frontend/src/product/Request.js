import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useState } from 'react';
import { useEffect } from 'react';
import { useRef } from 'react';
import axios from 'axios';
import AddStyle from './AddStyle.css';
import ErrorBox from './ErrorBox';

function Request(prop) {
    const [customerId, setCustomerId] = useState(prop.customerId);
    const [serialNumber, setSerialNumber] = useState("");
    const [productName, setProductName] = useState("");
    const [productType, setProductType] = useState("");
    const [manufacturerId, setManufacturerId] = useState(null);
    const [warrentyOrGuaranteeExpiry, setWarrentyOrGuaranteeExpiry] = useState("");
    const [purchaseDate, setPurchaseDate] = useState("");
    const [receipt, setReceipt] = useState(null);
    const [message, setMessage] = useState("");
    const [product, setProduct] = useState({});

    const nameRef = useRef(null);
    const typeRef = useRef(null);
    const serialRef = useRef(null);
    const warrentyRef = useRef(null);
    const purchaseRef = useRef(null);
    const manufacturerRef = useRef(null);

    const dateRef = useRef(null);
    const statusRef = useRef(null);
     useEffect(() => {
        getProductByid();
    }, []);

    function getProductByid() {
        let id = prop.productId;
        axios.get(`http://localhost:8080/product/${id}`).then((response) => {
            // setProduct(response.data);
            console.log("response", response);
        }).catch((error) => {
            console.log("error", error);
        });
    }

    function submitHandler(e) {
        e.preventDefault();
        const requestDto = {customerId:product.customerId, 
            manufacturerId:product.manufacturerId, 
            productId:product.id, 
            startDate:dateRef.current.value,
            type:statusRef.current.value,
            status:"OPEN"
        };

        console.log(requestDto)

        //console.log("form data " + formData.get(productName));

        axios.post("http://localhost:8080/customer/addRequest", JSON.stringify(requestDto), {headers:{ "Content-Type": 'application/json' }}).then((response) => {
            console.log(response);
            //console.log(response.data.message);
            return (prop.view());
         }).catch((error) => {
            console.log(error);
             console.log(error.response.data.message);
            setMessage(error.response.data.message);
         });
    }

    return (
        <div>
            <center><h1>Edit Product</h1></center>
            <Form onSubmit={submitHandler}>

                <Form.Group className="mb-3" controlId="formBasicText">
                    <Form.Label>Start Date:</Form.Label>
                    <Form.Control type="date" defaultValue={product.purchaseDate} ref={dateRef} required />
                </Form.Group>

                <Form.Select aria-label="Default select example" ref={statusRef} >
                    <option>Request Type</option>
                    <option value="MAINTENANCE">MAINTENANCE</option>
                    <option value="REPAIR">REPAIR</option>
                </Form.Select>

                <br></br>
                
                <Button variant="primary" type="submit" onClick={()=> {prop.requestForm()}}>Submit Request</Button>&nbsp; &nbsp;
                <Button variant="primary" onClick={() => { prop.view() }}>Back</Button>
            </Form>
        </div>
    );
}

export default Request;