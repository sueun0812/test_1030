import axios from 'axios';
import React, { useEffect, useState } from 'react'

const DOMAIN = 'http://localhost:8080';
const BOOK_API = 'api/test/books';

interface GetBookListResponseDto {
  id: number;
  book_title: string;
  book_author: string;
}

type Category = '인문' | '사회' | '과학기술' | '기타';

export default function SearchFullBook() {
  const [query, setQuery] = useState<Category>('인문');
  const [category, setCategory] = useState<string>('');
  const [results, setResults] = useState<GetBookListResponseDto[]>([]);
  
  const handleCategoryChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setCategory(e.target.value);
  }

  const fetchBooks = async (category: string) => {
    if (category.trim()) {
      try {

        const response = await axios.get(
          `${DOMAIN}/${BOOK_API}/`,
          { params: { category }}
        );

        const data = response.data.data;
        setResults(data);
      } catch (error) {
        console.error("Error fetching data: ", error);
      }
    }
  }

  useEffect(() => {
    fetchBooks(query);
  }, [query]);

  const handleButtonClick = (e:React.MouseEvent<HTMLButtonElement>) => {
    const selectedCategory = e.currentTarget.value as Category;
    setQuery(selectedCategory);
  }

  return (
    <div>
      <div>
        <button 
          value='인문'
          onClick={handleButtonClick}
        >인문</button>
        <button 
          value='사회' 
          onClick={handleButtonClick}
        >사회</button>
        <button 
          value='과학기술' 
          onClick={handleButtonClick}
        >과학기술</button>
        <button 
          value='기타' 
          onClick={handleButtonClick}
        >기타</button>
      </div>

      <input 
        type="text"
        value={category}
        onChange={handleCategoryChange}  
        placeholder='Enter Category'
        required
      />

      <ul>
        {results.map((result, index) => (
          <li key={index}>{result.book_title}</li>
        ))}
      </ul>
    </div>
  )
}