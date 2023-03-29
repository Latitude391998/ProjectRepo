import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {useState} from 'react';
import axios from "../API/axios";
import AddStyle from './AddStyle.css';
import ErrorBox from './ErrorBox';

function Add(prop)
{
    const [customerId, setCustomerId] = useState(prop.customerId);
    const [serialNumber, setSerialNumber] = useState("");
    const [productName, setProductName] = useState("");
    const [productType, setProductType] = useState("");
    const [manufacturerId, setManufacturerId] = useState(0);
    const [warrentyOrGuaranteeExpiry, setWarrentyOrGuaranteeExpiry] = useState("");
    const [purchaseDate, setPurchaseDate] = useState("");
    const [receipt, setReceipt] = useState(null);
    const [message, setMessage] = useState("");

    function addProduct(e)
    {
        e.preventDefault();
        const formData = new FormData();
        console.log(customerId);
        formData.append('customerId',customerId);
        formData.append('serialNumber',serialNumber);
        formData.append('productName',productName);
        formData.append('productType',productType);
        formData.append('manufacturerId',manufacturerId);
        console.log("manufacturerId"+manufacturerId);
        formData.append('warrentyOrGuaranteeExpiry',warrentyOrGuaranteeExpiry);
        formData.append('purchaseDate',purchaseDate);
        formData.append('receipt',receipt);

        axios.post("/product",formData, {headers: { "Content-Type": "multipart/form-data" }}).then((response)=>
        {
            console.log(response);
            console.log(response.data.message);
            prop.view();
        }).catch((error)=>{
            console.log(error);
            console.log(error.response.data.message);
            setMessage(error.response.data.message);
        });
    }

    return(
        <div>
            <center><h1>Add Product</h1></center>
        <Form onSubmit={addProduct}>
            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Product Name:</Form.Label>
                <Form.Control type="text" placeholder="Enter Name" onChange={(e)=>{setProductName(e.target.value)}} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Product Type:</Form.Label>
                <Form.Control type="text" placeholder="Enter Type" onChange={(e)=>{setProductType(e.target.value)}} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Serial Number:</Form.Label>
                <Form.Control type="text" placeholder="Enter Serial Number" onChange={(e)=>{setSerialNumber(e.target.value)}} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Warrenty OR Guarantee Expiry:</Form.Label>
                <Form.Control type="date" onChange={(e)=>{setWarrentyOrGuaranteeExpiry(e.target.value)}} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Purchase Date:</Form.Label>
                <Form.Control type="date" onChange={(e)=>{setPurchaseDate(e.target.value)}} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Manufacturer Id:</Form.Label>
                <Form.Control type="number" placeholder="Enter Manufacturer Id" onChange={(e)=>{setManufacturerId(e.target.value)}} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Product Receipt:</Form.Label>
                <Form.Control type="file" onChange={(e)=>{setReceipt(e.target.files[0])}} required/>
            </Form.Group>
            <ErrorBox error={message}></ErrorBox>
            <Button variant="primary" type="submit">Add product</Button>&nbsp; &nbsp;
            <Button variant="primary" onClick={()=>{prop.view()}}>Back</Button>
        </Form>
        </div>
    );
}
export default Add;