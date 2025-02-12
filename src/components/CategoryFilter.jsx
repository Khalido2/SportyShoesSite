export default function CategoryFilter({categories, onFilterChange}){

    return(
        <h3>Filter
            <select onChange={onFilterChange}>
            {categories.map((category) => (
            <option key={category.id} value={category.id}>{category.name}</option>
            ))}
            </select> 
        </h3>
    )
}