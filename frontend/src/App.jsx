import { useEffect, useState } from 'react'
import { Center, Flex, Grid, Text } from '@chakra-ui/react'

import SuGrid from './components/SuGrid';
import Square from './components/Square';
import Section from './components/Section';

function App() {
  const [useGrid, setGrid] = useState([
    [1, 2, 3, 4, 5, 6, 7, 8, 9],
    [10, 11, 12, 13, 14, 15, 16, 17, 18],
    [19, 20, 21, 22, 23, 24, 25, 26, 27],
    [28, 29, 30, 31, 32, 33, 34, 35, 36],
    [37, 38, 39, 40, 41, 42, 43, 44, 45],
    [46, 47, 48, 49, 50, 51, 52, 53, 54],
    [55, 56, 57, 58, 59, 60, 61, 62, 63],
    [64, 65, 66, 67, 68, 69, 70, 71, 72],
    [73, 74, 75, 76, 77, 78, 79, 80, 81]
  ]);

  const [useTool, setTool] = useState(['Edi', 'Mar', 'Era']);

  const [useBut, setBut] = useState([1, 2, 3, 4, 5, 6, 7, 8, 9]);

  const isPortrait = window.innerHeight > window.innerWidth

  const [useRot, setRot] = useState(isPortrait);

  useEffect(() => {
    setRot(!useRot);
  }, [isPortrait])

  return (
    <Center w={'100%'} h={'100vh'} flexDir={useRot ? 'row' : 'column'} gap={{ base: 5, smToMd: '1rem', md: '2rem' }}>

      <Text fontWeight={'medium'} fontSize={'5xl'}>Sudoku</Text>
      <SuGrid >
        {
          useGrid?.map((rows, ind) => {
            return rows?.map((cell, ind) => {
              return (<Square key={'posInd' + ind}>{cell}</Square>)
            })
          })
        }
      </SuGrid>
      <Flex flexDir={useRot ? 'row' : 'column'} bg={'blue'} borderRadius={'xl'} p={{ base: 2, smToMd: 2, md: 3 }}>


        <Section rotation={useRot}>{
          useTool?.map((item, ind) => {
            return (<Square key={'toolButtInd' + ind}>{item}</Square>)
          })
        }
        </Section>
        <Section rotation={useRot} mt={useRot ? '' : { base: 2, smToMd: 2, md: 3 }} ms={useRot ? { base: 2, smToMd: 2, md: 3 } : ''}>{
          useBut?.map((item, ind) => {
            return (<Square orokey={'buttInd' + ind}>{item}</Square>)
          })
        }
        </Section>

      </Flex>

    </Center>
  )
}

export default App
