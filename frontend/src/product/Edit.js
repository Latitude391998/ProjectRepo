import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {useState} from 'react';
import {useEffect} from 'react';
import {useRef} from 'react';
import axios from 'axios';
import AddStyle from './AddStyle.css';
import ErrorBox from './ErrorBox';

function Edit(prop)
{
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

    useEffect(()=>
    {
        getProductByid();
    },[]);

    function getProductByid()
    {
        let id = prop.updateId;
        axios.get(`http://localhost:8080/product/${id}`).then((response) => {
            setProduct(response.data);
            console.log("response", response);
            }).catch((error) => {
            console.log("error", error);
        });
    }

    function submitHandler(e)
    {
        e.preventDefault();
        const formData = new FormData();
        formData.append('id',prop.updateId);
        formData.append('customerId',customerId);
        formData.append('serialNumber',serialRef.current.value);
        formData.append('productName',nameRef.current.value);
        formData.append('productType',typeRef.current.value);
        formData.append('manufacturerId',manufacturerRef.current.value);
        formData.append('warrentyOrGuaranteeExpiry',warrentyRef.current.value);
        formData.append('purchaseDate',purchaseRef.current.value);
        formData.append('receipt',receipt);

        console.log("form data "+formData.get(productName));

        axios.put("http://localhost:8080/product",formData).then((response)=>
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
            <center><h1>Edit Product</h1></center>
        <Form onSubmit={submitHandler}>
            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Product Name:</Form.Label>
                <Form.Control type="text" placeholder="Enter Name" defaultValue={product.productName} ref={nameRef} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Product Type:</Form.Label>
                <Form.Control type="text" placeholder="Enter Type" defaultValue={product.productType} ref={typeRef} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Serial Number:</Form.Label>
                <Form.Control type="text" placeholder="Enter Serial Number" defaultValue={product.serialNumber} ref={serialRef} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Warrenty OR Guarantee Expiry:</Form.Label>
                <Form.Control type="date" defaultValue={product.warrentyOrGuaranteeExpiry} ref={warrentyRef} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Purchase Date:</Form.Label>
                <Form.Control type="date" defaultValue={product.purchaseDate} ref={purchaseRef} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Manufacturer Id:</Form.Label>
                <Form.Control type="number" defaultValue={product.manufacturerId} placeholder="Enter Manufacturer Id" ref={manufacturerRef} required/>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicText">
                <Form.Label>Product Receipt:</Form.Label>
                <Form.Control type="file" onChange={(e)=>{setReceipt(e.target.files[0])}} required/>
            </Form.Group>
            <ErrorBox error={message}></ErrorBox>
            <Button variant="primary" type="submit">Update product</Button>&nbsp; &nbsp;
            <Button variant="primary" onClick={()=>{prop.view()}}>Back</Button>
        </Form>
        </div>
    );
}

export default Edit;