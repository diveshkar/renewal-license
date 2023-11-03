import React, { useState, useRef } from 'react';

const CameraAccess = () => {
  const videoRef = useRef(null);
  const canvasRef = useRef(null);
  const [mediaStream, setMediaStream] = useState(null);

  const startCamera = async () => {
    try {
      const stream = await navigator.mediaDevices.getUserMedia({ video: true });
      videoRef.current.srcObject = stream;
      setMediaStream(stream);
    } catch (error) {
      console.error('Error accessing the camera:', error);
    }
  };

  const stopCamera = () => {
    if (mediaStream) {
      mediaStream.getTracks().forEach((track) => {
        track.stop();
      });
    }
  };

  const capturePhoto = () => {
    if (mediaStream) {
      const context = canvasRef.current.getContext('2d');
      context.drawImage(videoRef.current, 0, 0, 640, 480);

      // Convert the canvas content to a data URL representing a PNG image
      const dataURL = canvasRef.current.toDataURL('image/png');

      // Create a temporary <a> element to trigger the download
      const link = document.createElement('a');
      link.href = dataURL;
      link.download = 'captured_photo.png';

      // Trigger a click on the link to initiate the download
      link.click();
    }
  };

  return (
    <div>
      <button onClick={startCamera}>Start Camera</button>
      <button onClick={stopCamera}>Stop Camera</button>
      <button onClick={capturePhoto}>Capture Photo</button>
      <video ref={videoRef} autoPlay />
      <canvas ref={canvasRef} width="640" height="480" style={{ display: 'none' }} />
    </div>
  );
};

export default CameraAccess;
