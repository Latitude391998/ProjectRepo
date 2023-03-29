import axios from 'axios';
import {useState} from 'react';
import {useEffect} from 'react';

function Receipt(prop)
{
    const [receipt, setReceipt] = useState("");

    useEffect(()=>{
        getImage();
    },[]);

    function getImage()
    {
        axios.get(`http://localhost:8080/product/${prop.receiptId}`).then((response)=>{
            console.log(response.data.receipt);
            console.log(response);
            setReceipt(response.data.receipt);
        }).catch((error)=>
        {
            console.log(error);
        });
    }
    return(
        <div>
            <center>
            <h1>Product Receipt</h1>
            <button type="button" class="btn btn-primary" onClick={prop.view}>Back</button><br/><br/>
            <img src={receipt} style={{ width: '400px', height: 'auto', objectFit: 'cover' }}></img>
            </center>
        </div>
    );
}

export default Receipt;