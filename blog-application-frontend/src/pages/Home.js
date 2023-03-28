import React, { useEffect } from "react";
import { Container } from "reactstrap";
import NewFeed from "../components/NewFeed";

const Home = () => {
  return (
    <Container className="mt-3" >
      <NewFeed />
    </Container>
  );
};

export default Home;
