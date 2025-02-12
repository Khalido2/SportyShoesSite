import { useEffect, useState } from "react";
import Table from 'react-bootstrap/Table';
import { fetchPayments, fetchPaymentsByCategoryId } from "../../services/PaymentService";
import CategoryFilter from '../CategoryFilter';

export default function PaymentHistoryTable({categories}){

    const filterStartDate = new Date();
    filterStartDate.setMonth(-filterStartDate.getMonth()*6) //goes back six months

    const startDateText = filterStartDate.toISOString().split('T')[0]

    const [searchDate, setSearchDate] = useState(filterStartDate)
    const [payments, setPayments] = useState([]);

    useEffect(() => {
        getPayments();
    }, [])

    const getPayments = async() => {
        const data = await fetchPayments();
        setPayments(data)
    }

    const getPaymentsInCategory = async(categoryId) => {
        const data = await fetchPaymentsByCategoryId(categoryId)
        setPayments(data)
    }

    const handleCategoryFilterChange = (e) => {
        getPaymentsInCategory(e.target.value)
    }

    const handleDateChange = (event) => {
        setSearchDate(event.target.value);
      };
    
      //filter between now and that filtered date
      const filteredPayments = payments.filter((payment) => {
        return (new Date(payment.purchaseDate) > new Date(searchDate))
      });
    
    return (
    <>
    <br/>
        <CategoryFilter onFilterChange={handleCategoryFilterChange} categories={categories}/>
        <h3>Since
        <input type="date" value={startDateText} onChange={handleDateChange}/>
        </h3>
       
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>#</th>
              <th>Purchaser</th>
              <th>Item Purchased</th>
              <th>Amount Paid</th>
              <th>Date</th>
            </tr>
          </thead>
          <tbody>
          {
          filteredPayments.map((item, index) => (
                    <tr key={index}>
                        <td>{item.id}</td>
                        <td>{item.purchaser.name}</td>
                        <td>{item.itemPurchased.name}</td>
                        <td>£{item.amountPaid}</td>
                        <td>{item.purchaseDate}</td>
                    </tr>
                ))
        }
          </tbody>
        </Table>
    </>    
      );
  }