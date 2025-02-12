import { useEffect, useState } from "react";
import PaymentHistoryTable from "../components/Payments/PaymentHistoryTable"
import { fetchCategories } from '../services/CategoryService';

export default function PaymentHistory({loggedIn}){
    const[categories, setCategories] = useState([])

    useEffect(() => {
        getCategories();
    }, [])

    const getCategories = async() => {
        const data = await fetchCategories();
        setCategories(data)
    }
    
    const displayPaymentData = () => {
        if (loggedIn){
          return <PaymentHistoryTable categories={categories}/>
        } else{
          return <div>Log in to see payment records</div>
        }
    }

    return (
        <div>
            <h1>Records</h1>
            {displayPaymentData()}
        </div>
    )
}