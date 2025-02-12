import { useEffect, useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { addShoe } from "../../services/ShoeService"

export default function ShoeProductForm({categories, fetchShoes}) {
      const[image, setImage] = useState(null)
      const[name, setName] = useState("")
      const[description, setDescription] = useState("")
      const[price, setPrice] = useState("")
      const[shoeCategory, setCategory] = useState(2)

      const handleFileChange = (e) => {
        setImage(e.target.files[0]);
      }
    
      const handleCategoryChange = (e) => {
        setCategory(e.target.value)
      }

  const handleSubmit = async(e) => {
    e.preventDefault();
    
    const result = await addShoe(name, description, price, image, shoeCategory);
    
    if(result){
      alert("Successfully upload shoe!")
      fetchShoes();
    }else{
      alert("Failed to upload shoe")
    }
  }

    return (

        <div>
            {/* Here we need some form to add shoes  */}
            <h1>Add a new shoe</h1>
                <Form onSubmit={handleSubmit}>
                    <Form.Group className='mb-3'>
                      <Form.Label>Product Name</Form.Label>
                      <Form.Control type='text' placeholder='Super Cool Shoey' onChange={(e) => setName(e.target.value)} required/>
                    </Form.Group>

                    <Form.Group className='mb-3'>
                      <Form.Label>Description</Form.Label>
                      <Form.Control type='text' placeholder='What kind of shoe...' onChange={(e) => setDescription(e.target.value)} required/>
                    </Form.Group>

                    <Form.Group className='mb-3'>
                      <Form.Label>Category</Form.Label>
                        <Form.Select onChange={handleCategoryChange}>
                        {categories.map((category) => (
                        <option key={category.id} value={category.id}>{category.name}</option>
                        ))}
                        </Form.Select> 
                    </Form.Group>

                    <Form.Group className='mb-3'>
                      <Form.Label>Price</Form.Label>
                      <Form.Control step={0.01} type='number' onChange={(e) => setPrice(e.target.value)} required/>
                    </Form.Group>
              
                    <Form.Group className='mb-3'>
                      <Form.Label>Image</Form.Label>
                      <Form.Control type='file' onChange={handleFileChange} required/>
                    </Form.Group>

                    <Button variant="primary" type="submit">Upload Shoe</Button>
                </Form>

                <br></br>
        </div>
    
    )
}