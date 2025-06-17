import { useEffect, useState } from 'react';
import { Post, Put } from '@/services';

function Form() {
  const [title, setTitle] = useState('');
  const [price, setPrice] = useState('');
  const [uploading, setUploading] = useState(false);
  const [uploadedUrl, setUploadedUrl] = useState<string | null>(null);
  useEffect(() => {
    console.log('업로드된 URL:', uploadedUrl);
  }, [uploadedUrl]);
  const handleSubmit = () => {
    if (!title || !price) {
      alert('제목과 가격을 입력하세요.');
      return;
    }
    if (!uploadedUrl) {
      alert('파일을 먼저 업로드하세요.');
      return;
    }
    Post('/add', { title, price, filename: uploadedUrl })
      .then((response: any) => {
        console.log('성공:', response.data);
        window.location.href = '/';
        window.scrollTo(0, document.body.scrollHeight);
      })
      .catch((error: any) => {
        console.error('에러:', error);
      });
  };
  const handleFileUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const files = e.target.files;
    if (!files || files.length === 0) return;

    setUploading(true);
    try {
      // 1. presigned URL 요청
      const { data } = await Post('/presignedURL', { fileName: files[0].name });
      const presignedUrl = data.url;
      // 2. S3에 파일 업로드
      await Put(presignedUrl, files[0]);
      // 3. 업로드된 파일의 실제 S3 URL 추출 (쿼리스트링 제거)
      const fileUrl = presignedUrl.split('?')[0];
      setUploadedUrl(fileUrl);
      alert('파일 업로드 성공!');
    } catch (error) {
      console.error('파일 업로드 실패:', error);
      alert('파일 업로드 실패');
    } finally {
      setUploading(false);
    }
  };

  return (
    <form>
      <input name="title" value={title} onChange={(e) => setTitle(e.target.value)} placeholder="제목" />
      <input name="price" value={price} onChange={(e) => setPrice(e.target.value)} placeholder="가격" type="number" />
      <input name="file" type={'file'} onChange={(e) => handleFileUpload(e)} />
      <button type="button" onClick={handleSubmit}>
        버튼
      </button>
    </form>
  );
}

export default Form;
