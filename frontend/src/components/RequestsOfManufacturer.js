import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import axios from "../API/axios";
// import AuthContext from "../context/AuthProvider"

const RequestsOfManufacturer = () => {
  let requestList = [];
  //    const [msg, setMsg] = useState("");

  // const manufacturer = localStorage.getItem("manufacturer_info");

  // useEffect(() => {
  //     try {
  //         const response = axios.get("/manufacturer/requests", JSON.stringify(manufacturer),
  //             {
  //                 headers: { "Content-Type": 'application/json' }
  //             });

  //             localStorage.setItem("manufacturer_request_list",JSON.stringify((response).data));

  //     } catch (error) {
  //         setMsg("Invalid Email Id or Password");
  //     }

  //      requestList = localStorage.getItem("manufacturer_request_list");
  // }, [requestList]);

  return (
    <>
      <div>
        <br /><br />
        <div className="container" align="center">

          <div className="card">

            <div className="card-header">
              <h1>Requests</h1>
            </div>

            <div className="card-body">
              <table>
                <thead>
                  <tr>
                    <th>heading1</th>
                    <th>heading2</th>
                  </tr>
                </thead>
                <tbody>
                  {requestList.map((item) => {
                    return (
                      <tr>
                        <td>{item.id}</td>
                        <td>
                          <Link to={"editRequest/" + item.id}>Update</Link>
                        </td>
                      </tr>
                    );
                  })}
                </tbody>
              </table>

            </div>
          </div>
        </div>
      </div>

    </>
  );
};

export default RequestsOfManufacturer;
