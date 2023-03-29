import {useState} from 'react';
import {useEffect} from 'react';
import Button from 'react-bootstrap/Button';
import axios from 'axios';

function View(props)
{
    const [products, setProducts] = useState([]);
    const [productId, setProductId] = useState(null);
    const [customerId, setCustomerId] = useState(null);
    const [serialNumber, setSerialNumber] = useState("");
    const [productName, setProductName] = useState("");
    const [productType, setProductType] = useState("");
    const [manufacturerId, setManufacturerId] = useState("");
    const [dateOfManufacture, setDateOfManufacture] = useState("");
    const [purchaseDate, setPurchaseDate] = useState("");

    useEffect(()=>
    {
        getProducts();
    }, []);

    function getProducts()
    {
        fetch("http://localhost:8080/product").then((result)=>{
        result.json().then((response)=>
        {
            setProducts(response);
            console.log(response)
            // setProductId(response[0].id);
            // setProductName(response[0].productName);
            // setProductType(response[0].productType);
        });
        });
    }

    function deleteProduct(id)
    {
        axios.delete(`http://localhost:8080/product/${id}`).then((response)=>
        {
            console.log(response.data.message);
            getProducts();
        }).catch((error)=>{
            console.log(error.response.data.message);
        })
    }

    function addRequest(id){

    }

    return(
        <div>
            <center><h1>Products</h1></center>
        <Button variant="primary" className='Add' onClick={()=>{props.add()}}>Add Product</Button>
        <br/>
        <table className="table">
                <thead>
                <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Type</th>
                <th scope="col">Serial Number</th>
                <th scope="col">Manufacturer Id</th>
                <th scope="col">Warrenty/Guarantee Expiry</th>
                <th scope="col">Purchase Date</th>
                <th scope="col" colSpan="4">Operations</th>
                </tr>
                </thead>
                <tbody>
                {
                    products.map((item, index)=>
                    {
                        return(
                            <tr key={index}>
                            <td>{item.id}</td>
                            <td>{item.productName}</td>
                            <td>{item.productType}</td>
                            <td>{item.serialNumber}</td>
                            <td>{item.manufacturerId}</td>
                            <td>{item.warrentyOrGuaranteeExpiry}</td>
                            <td>{item.purchaseDate}</td>
                            <td><Button variant="primary" onClick={()=>{props.receipt(item.id)}}>View Receipt</Button></td>
                            <td><Button variant="primary" onClick={()=>{props.edit(item.id)}}>Edit</Button></td>
                            <td><Button variant="primary" onClick={()=>{props.request(item.id)}}>Add Request</Button></td>
                            <td><Button variant="primary" onClick={()=>{deleteProduct(item.id)}}>Delete</Button></td>
                        </tr>
                        );
                    })
                }
                </tbody>
            </table>
        </div>
    );
} 

export default View;