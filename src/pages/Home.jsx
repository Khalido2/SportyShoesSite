import { useEffect, useState } from 'react';
import ShoeView from "../components/ShoeView/ShoeView";
import { fetchShoes, fetchShoesInCategory, getShoeImagePath } from "../services/ShoeService"
import ShoeProductForm from '../components/ShoeView/ShoeProductForm';
import CategoryFilter from '../components/CategoryFilter';
import { fetchCategories } from '../services/CategoryService';

export default function Home({loggedIn}){
  const[shoes, setShoes] = useState([])
  const[categories, setCategories] = useState([])

  useEffect(() => {
  getCategories();
  }, [])

  useEffect(() => {
    getShoes();
  }, [])

  const getCategories = async() => {
    const data = await fetchCategories();
    setCategories(data)
    }

  const getShoes = async() => {
    const data = await fetchShoes();
    setShoes(data);
  }

  const displayAdminView = () => {
    if (loggedIn){
      return <ShoeProductForm categories={categories} fetchShoes={getShoes}/>
    } else{
      return <div/>
    }
  }

  const handleCategoryFilterChange = (e) => {
    getShoesInCategory(e.target.value)
  }

  const getShoesInCategory = async(categoryId) => {
    const result = await fetchShoesInCategory(categoryId)
    setShoes(result)
  }

  return (
    <div>
      {displayAdminView()}

      <CategoryFilter onFilterChange={handleCategoryFilterChange} categories={categories}/>

      <div className="grid-container">
      {shoes.map((shoe) => (
        <ShoeView key={shoe.id} name={shoe.name} description={shoe.description} price={shoe.price} imagePath={getShoeImagePath(shoe.imagePath)}/>
      ))}
      </div>
    </div>
  );
}