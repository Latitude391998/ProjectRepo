import {useState} from 'react';
import {useEffect} from 'react';
import View from './View';
import Button from 'react-bootstrap/Button';
import ProductStyle from './ProductStyle.css';
import Add from './Add';
import React from 'react';
import Edit from './Edit';
import Receipt from './Receipt';
import Request from './Request';


function Product(prop)
{
    const[isViewDisplay,setIsViewDisplay] = useState("");
    const[updateId, setUpdateId] = useState(null);
    const[receiptId, setReceiptId] = useState(null);
    const[productId, setProductId] = useState(null);
    function showAddPage()
    {
        setIsViewDisplay("addpage");
    }

    function showViewPage()
    {
        setIsViewDisplay("viewpage");
    }

    function showEditPage(id)
    {
        setIsViewDisplay("edit");
        console.log(id);
        setUpdateId(id);
    }

    function showReceipt(id)
    {
      setReceiptId(id);
      setIsViewDisplay("receipt");
    }

    function showRequestForm(id){
      setIsViewDisplay("request");
      setProductId(id);
    }


    return(
        <div className="product">
            {
               (() => {
                switch (isViewDisplay) {
                    case 'addpage':
                      return <Add view={showViewPage} customerId={prop.customerId} className="add"/>;
                    case 'viewpage':
                      return <View add={showAddPage} edit={showEditPage} receipt={showReceipt} request={showRequestForm} className="view"/>;
                    case 'edit' :
                      return <Edit view={showViewPage} updateId={updateId} customerId={prop.customerId}></Edit>;
                    // case 'request' :
                    //     return <Request view={showViewPage} requestForm={showRequestForm} productId={productId}></Request>;
                    case 'receipt' :
                      return <Receipt receiptId={receiptId} view={showViewPage}></Receipt>
                    default:
                      return <View add={showAddPage} edit={showEditPage} receipt={showReceipt} request={showRequestForm} className="view"/>;
                }
                })()
            }
            
        </div>
    );
}

export default Product;