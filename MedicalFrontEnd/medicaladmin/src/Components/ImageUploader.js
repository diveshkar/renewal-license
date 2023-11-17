import React, { useState, useRef } from 'react';

function ImageUploader() {
  const [image, setImage] = useState(null);
  const inputRef = useRef(null);

  const handleImageChange = (e) => {
    const file = e.target.files[0];

    if (file) {
      const reader = new FileReader();

      reader.onload = () => {
        setImage(reader.result);
      };

      reader.readAsDataURL(file);
    }
  };

  const clearImage = () => {
    setImage(null);
    // Reset the input value to allow uploading the same image again
    if (inputRef.current) {
      inputRef.current.value = null;
    }
  };

  return (
    <div>
      <input type="file" onChange={handleImageChange} ref={inputRef} />
      <br />

      {image && (
        <div>
          <img src={image} alt="Uploaded" />
          <br />
          <button onClick={clearImage}>Clear Image</button>
        </div>
      )}
    </div>
  );
}

export default ImageUploader;
